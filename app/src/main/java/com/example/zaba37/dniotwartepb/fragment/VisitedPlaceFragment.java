package com.example.zaba37.dniotwartepb.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zaba37.dniotwartepb.R;
import com.example.zaba37.dniotwartepb.activity.ScannerActivity;
import com.example.zaba37.dniotwartepb.adapter.VisitedPlaceAdapter;

public class VisitedPlaceFragment extends Fragment {
    private FloatingActionButton fbScanner;
    private TextView tvEmptyView;
    private VisitedPlaceAdapter listAdapter;
    private ListView placeList;

    public VisitedPlaceFragment() {}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listAdapter = new VisitedPlaceAdapter(getActivity());
        placeList.setAdapter(listAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View visitedPlaceFragmentView = inflater.inflate(R.layout.fragment_visited_palce, container, false);
        placeList = (ListView) visitedPlaceFragmentView.findViewById(R.id.lvPlaceList);
        tvEmptyView = (TextView) visitedPlaceFragmentView.findViewById(R.id.tvEmptyView);
        fbScanner = (FloatingActionButton) visitedPlaceFragmentView.findViewById(R.id.fbScanner);

        fbScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(getActivity(), ScannerActivity.class));
            }
        });

        return visitedPlaceFragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
