package com.greenApp.zaba37.dzienotwartypb.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.greenApp.zaba37.dzienotwartypb.Constants;
import com.greenApp.zaba37.dzienotwartypb.R;
import com.greenApp.zaba37.dzienotwartypb.model.MarkerDetails;
import com.greenApp.zaba37.dzienotwartypb.model.MarkerDialog;
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
//        tileView.setSize(3000, 2228);

        tileView.setBackgroundColor(Color.WHITE);

        tileView.addDetailLevel(0.1250f, "map/125/tile-%d_%d.png");
        tileView.addDetailLevel(0.2500f, "map/250/tile-%d_%d.png");
        tileView.addDetailLevel(0.5000f, "map/500/tile-%d_%d.png");
        tileView.addDetailLevel(1.0000f, "map/1000/tile-%d_%d.png");

//        tileView.addDetailLevel(0.1250f, "map/125/%d_%d.png");
//        tileView.addDetailLevel(0.2500f, "map/250/%d_%d.png");
//        tileView.addDetailLevel(0.5000f, "map/500/%d_%d.png");
//        tileView.addDetailLevel(1.0000f, "map/1000/%d_%d.png");

        //tileView.defineBounds(0, 0, 1, 1);
        tileView.defineBounds(0, 0, 2018, 1499);
        //tileView.defineBounds(0,0,2018,1499);
        tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
        tileView.setScale(0.0f);

        tileView.setScaleLimits(0, 4);

        ImageView cr = new ImageView(getActivity());
        ImageView wi = new ImageView(getActivity());
        ImageView wm = new ImageView(getActivity());
        ImageView we = new ImageView(getActivity());
        ImageView wb = new ImageView(getActivity());
        ImageView iet = new ImageView(getActivity());
        //ImageView cnk = new ImageView(getActivity());
        ImageView ew = new ImageView(getActivity());
        ImageView wa = new ImageView(getActivity());
        ImageView wz = new ImageView(getActivity());
        ImageView zwl = new ImageView(getActivity());

        //[]{x,y,latitude,longtidude,index}

        //CNK1 330 224
        //CNK2 370 224
        //CNK3 410 224
        //CNK4 450 224
        cr.setTag(new MarkerDetails(1420, 960-20, Constants.WI_LAT, Constants.WI_LONG, "Centrum Rekrutacji, Studiów Podyplomowych i Szkoleń", Constants.CENTRUM_REKTURTACJI_EVENT));
        wi.setTag(new MarkerDetails(1350, 890-20, Constants.WI_LAT, Constants.WI_LONG, "Wydział Informatyki", Constants.INFORMATYKI_EVENT));
        wm.setTag(new MarkerDetails(1070, 660-20, Constants.WM_LAT, Constants.WM_LONG, "Wydział Mechaniczny", Constants.MECHANICZNY_EVENT));
        we.setTag(new MarkerDetails(970, 580-20, Constants.WE_LAT, Constants.WE_LONG, "Wydział Elektryczny", Constants.ELEKTRYCZNY_EVENT));
        wb.setTag(new MarkerDetails(640, 305-20, Constants.WB_LAT, Constants.WB_LONG, "Wydział Budownictwa i Inżynierii Środowiska", Constants.BUDOWNICTWA_I_SRODOWISKA_EVENT));
        //iet.setTag(new MarkerDetails(973, 352, Constants.WB_LAT, Constants.WB_LONG, "Inno Eko Tech", Constants.ENERGIA_WIOSNY_EVENT));
        ew.setTag(new MarkerDetails(330, 224-20, Constants.CNK_LAT, Constants.CNK_LONG, "Energia Wiosny!", Constants.ENERGIA_WIOSNY_EVENT));
        wa.setTag(new MarkerDetails(370, 224-20, Constants.CNK_LAT, Constants.CNK_LONG, "Wydział Architektury", Constants.ARCHITEKTURY_EVENT));
        wz.setTag(new MarkerDetails(410, 224-20, Constants.CNK_LAT, Constants.CNK_LONG, "Wydział Zarządzania", Constants.ZARZADZANIA_EVENT));
        zwl.setTag(new MarkerDetails(450, 224-20, Constants.CNK_LAT, Constants.CNK_LONG, "Zamiejscowy Wydział Leśny", Constants.LESNY_EVENT));

        cr.setImageResource(R.drawable.custom_marker_1);
        wi.setImageResource(R.drawable.custom_marker_2);
        wm.setImageResource(R.drawable.custom_marker_3);
        we.setImageResource(R.drawable.custom_marker_4);
        wb.setImageResource(R.drawable.custom_marker_5);
        //iet.setImageResource(R.drawable.custom_marker_5);
        ew.setImageResource(R.drawable.custom_marker_9);
        wa.setImageResource(R.drawable.custom_marker_8);
        wz.setImageResource(R.drawable.custom_marker_7);
        zwl.setImageResource(R.drawable.custom_marker_6);

        tileView.getMarkerLayout().setMarkerTapListener(markerTapListener);

        tileView.addMarker(wi, 1350, 890-20, null, null);
        tileView.addMarker(cr, 1420, 960-20, null, null);
        tileView.addMarker(wm, 1070, 660-20, null, null);
        tileView.addMarker(we, 970, 580-20, null, null);
        tileView.addMarker(wb, 640, 305-20, null, null);
        //tileView.addMarker(iet, 973, 352, null, null);
        tileView.addMarker(ew, 330, 224-20, null, null);
        tileView.addMarker(wa, 370, 224-20, null, null);
        tileView.addMarker(wz, 410, 224-20, null, null);
        tileView.addMarker(zwl, 450, 224-20, null, null);


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


