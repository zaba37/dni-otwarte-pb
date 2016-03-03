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
        tileView.setSize(3560, 2993);

        tileView.setBackgroundColor(0xFFe7e7e7);

        tileView.addDetailLevel(0.1250f, "map/125/tile-%d_%d.png");
        tileView.addDetailLevel(0.2500f, "map/250/tile-%d_%d.png");
        tileView.addDetailLevel(0.5000f, "map/500/tile-%d_%d.png");
        tileView.addDetailLevel(1.0000f, "map/1000/tile-%d_%d.png");

        //tileView.defineBounds(0, 0, 1, 1);
        tileView.defineBounds(0, 0, 445, 374);
        tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
        tileView.setScale(0.0f);

        tileView.setScaleLimits(0, 4);

        ImageView wi = new ImageView(getActivity());
        ImageView wm = new ImageView(getActivity());
        ImageView we = new ImageView(getActivity());
        ImageView wb = new ImageView(getActivity());
        ImageView iet = new ImageView(getActivity());
        ImageView cnk = new ImageView(getActivity());

        wi.setTag(new double[]{337, 230});
        wm.setTag(new double[]{266, 170});
        we.setTag(new double[]{229, 136});
        wb.setTag(new double[]{187, 118});
        iet.setTag(new double[]{221, 104});
        cnk.setTag(new double[]{67, 40});

        wi.setImageResource(R.drawable.map_marker_normal);
        wm.setImageResource(R.drawable.map_marker_normal);
        we.setImageResource(R.drawable.map_marker_normal);
        wb.setImageResource(R.drawable.map_marker_normal);
        iet.setImageResource(R.drawable.map_marker_normal);
        cnk.setImageResource(R.drawable.map_marker_normal);

        tileView.getMarkerLayout().setMarkerTapListener(markerTapListener);

        tileView.addMarker(wi, 337, 230, null, null);
        tileView.addMarker(wm,266,170,null,null);
        tileView.addMarker(we,229,136,null,null);
        tileView.addMarker(wb,187,118,null,null);
        tileView.addMarker(iet,221,104,null,null);
        tileView.addMarker(cnk,67,40,null,null);


        tileView.setViewportPadding(1000);
        frame.addView(tileView);

        frameTo(0.5,0.5);

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
//            callout.setSubtitle( "Info window at coordinate:\n" + position[1] + ", " + position[0] );
        }
    };
}


