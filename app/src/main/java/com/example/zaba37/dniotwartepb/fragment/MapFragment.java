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

import com.example.zaba37.dniotwartepb.Constants;
import com.example.zaba37.dniotwartepb.R;
import com.example.zaba37.dniotwartepb.model.MarkerDetails;
import com.example.zaba37.dniotwartepb.model.MarkerDialog;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

public class MapFragment extends Fragment {
    private FrameLayout frame;
    private TileView tileView;

    public MapFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        frame = (FrameLayout) view.findViewById(R.id.frameMap);
        frame.removeAllViews();

        tileView = new TileView(getActivity());
        tileView.setSize(2018, 1499);

        tileView.setBackgroundColor(0xFFe7e7e7);

        tileView.addDetailLevel(0.1250f, "map/125/tile-%d_%d.png");
        tileView.addDetailLevel(0.2500f, "map/250/tile-%d_%d.png");
        tileView.addDetailLevel(0.5000f, "map/500/tile-%d_%d.png");
        tileView.addDetailLevel(1.0000f, "map/1000/tile-%d_%d.png");

        //tileView.defineBounds(0, 0, 1, 1);
        tileView.defineBounds(0, 0, 445, 374);
        //tileView.defineBounds(0,0,2018,1499);
        tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
        tileView.setScale(0.0f);

        tileView.setScaleLimits(0, 4);

        ImageView wi = new ImageView(getActivity());
        ImageView wm = new ImageView(getActivity());
        ImageView we = new ImageView(getActivity());
        ImageView wb = new ImageView(getActivity());
        ImageView iet = new ImageView(getActivity());
        ImageView cnk = new ImageView(getActivity());

        //[]{x,y,latitude,longtidude,index}
        wi.setTag(new MarkerDetails(337, 230, 53.117228, 23.146783, "Wydział Informatyczny", Constants.INFORMATYKI_EVENT));
        wm.setTag(new MarkerDetails(266, 170, 53.117646, 23.148751, "Wydział Mechaniczny", Constants.MECHANICZNY_EVENT));
        we.setTag(new MarkerDetails(229, 136, 53.117843, 23.149669, "Wydział Elektryczny", Constants.ELEKTRYCZNY_EVENT));
        wb.setTag(new MarkerDetails(187, 118, 53.118367, 23.152217, "Wydział Budownictwa i Inżynierii Środowiska", Constants.BUDOWNICTWA_I_SRODOWISKA_EVENT));
        iet.setTag(new MarkerDetails(221, 104, 53.117466, 23.152120, "Inno Eko Tech", Constants.ENERGIA_WIOSNY_EVENT));
        cnk.setTag(new MarkerDetails(67, 40, 53.118905, 23.154169, "Centrum Nowoczesnego Kształcenia", Constants.BUDOWNICTWA_I_SRODOWISKA_EVENT));

        wi.setImageResource(R.drawable.map_marker_normal);
        wm.setImageResource(R.drawable.map_marker_normal);
        we.setImageResource(R.drawable.map_marker_normal);
        wb.setImageResource(R.drawable.map_marker_normal);
        iet.setImageResource(R.drawable.map_marker_normal);
        cnk.setImageResource(R.drawable.map_marker_normal);

        tileView.getMarkerLayout().setMarkerTapListener(markerTapListener);

        tileView.addMarker(wi, 337, 230, null, null);
        tileView.addMarker(wm, 266, 170, null, null);
        tileView.addMarker(we, 229, 136, null, null);
        tileView.addMarker(wb, 187, 118, null, null);
        tileView.addMarker(iet, 221, 104, null, null);
        tileView.addMarker(cnk, 67, 40, null, null);


        tileView.setViewportPadding(1000);
        frame.addView(tileView);
        tileView.setScale(4.0f);

        frameTo(0.5, 0.5);

        tileView.setScale(1.0f);

        frameTo(0.5, 0.5);

        return view;
    }

    public TileView getTileView() {
        return tileView;
    }

    public void frameTo(final double x, final double y) {
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
        public void onMarkerTap(View view, int x, int y) {
            // get reference to the TileView
            TileView tileView = getTileView();
            //tileView.setScale(2);

            // we saved the coordinate in the marker's tag
            //double[] position = (double[]) view.getTag();
            MarkerDetails markerDetails = (MarkerDetails) view.getTag();
            // lets center the screen to that coordinate

            //tileView.slideToAndCenter(x, y);
            // create a simple callout
            MarkerDialog callout = new MarkerDialog(view.getContext(), markerDetails);
            // add it to the view tree at the same position and offset as the marker that invoked it
            tileView.addCallout(callout, markerDetails.getX(), markerDetails.getY(), -0.5f, -1.0f);
            // a little sugar
            callout.transitionIn();
            // stub out some text
            callout.setTitle(markerDetails.getTitle());

            tileView.setScale(4);
            tileView.moveToMarker(view, false);
        }
    };
}


