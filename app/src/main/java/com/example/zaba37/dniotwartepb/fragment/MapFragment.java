package com.example.zaba37.dniotwartepb.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.zaba37.dniotwartepb.R;
import com.example.zaba37.dniotwartepb.model.MarkerDialog;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

public class MapFragment extends Fragment {
    private FrameLayout frame;
    private TileView tileView;

    public MapFragment() {}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        frame = (FrameLayout) view.findViewById(R.id.frameMap);

        tileView = new TileView(getActivity());
        tileView.setSize(8967, 6726);

        tileView.setBackgroundColor(0xFFe7e7e7 );

        tileView.addDetailLevel(0.0125f, "map/phi-62500-%d_%d.jpg");
        tileView.addDetailLevel( 0.2500f, "map/phi-125000-%d_%d.jpg" );
        tileView.addDetailLevel( 0.5000f, "map/phi-250000-%d_%d.jpg" );
        tileView.addDetailLevel(1.0000f, "map/phi-500000-%d_%d.jpg");

        frame.setBackgroundColor(Color.BLUE);
        tileView.setScaleLimits(0, 2);

        // start small and allow zoom
        tileView.setScale( 0.3f );

        ImageView marker = new ImageView( getActivity() );
        marker.setTag(new double[] {2000, 2000});
        marker.setImageResource(R.drawable.map_marker_normal);
        tileView.getMarkerLayout().setMarkerTapListener( markerTapListener );
        tileView.addMarker( marker, 2000, 2000, null, null );
        frame.addView(tileView);
        return view;
    }

    public TileView getTileView(){
        return tileView;
    }

    public void frameTo( final double x, final double y ) {
        tileView.post(new Runnable() {
            @Override
            public void run() {
                tileView.scrollToAndCenter(x, y);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
        tileView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        tileView.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tileView.destroy();
        tileView = null;
    }

    private MarkerLayout.MarkerTapListener markerTapListener = new MarkerLayout.MarkerTapListener() {

        @Override
        public void onMarkerTap( View view, int x, int y ) {
            // get reference to the TileView
            TileView tileView = getTileView();
            // we saved the coordinate in the marker's tag
            double[] position = (double[]) view.getTag();
            // lets center the screen to that coordinate
            tileView.slideToAndCenter( position[0], position[1] );
            // create a simple callout
            MarkerDialog callout = new MarkerDialog( view.getContext() );
            // add it to the view tree at the same position and offset as the marker that invoked it
            tileView.addCallout( callout, position[0], position[1], -0.5f, -1.0f );
            // a little sugar
            callout.transitionIn();
            // stub out some text
            callout.setTitle( "Wydział Infomatyczny" );
            callout.setSubtitle( "Info window at coordinate:\n" + position[1] + ", " + position[0] );
        }
    };
}


