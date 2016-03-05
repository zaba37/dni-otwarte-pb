package com.greenApp.zaba37.dzienotwartypb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.greenApp.zaba37.dzienotwartypb.R;

/**
 * Created by zaba3 on 05.03.2016.
 */
public class MoreEventsFragment extends Fragment {

    public MoreEventsFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_events, container, false);
        WebView wvMoreEvents = (WebView) view.findViewById(R.id.wvMoreEvents);
        wvMoreEvents.loadUrl("file:///android_asset/inne.html");
        return view;
    }
}
