package com.greenApp.zaba37.dzienotwartypb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.greenApp.zaba37.dzienotwartypb.R;

/**
 * Created by zaba3 on 04.03.2016.
 */
public class MrWallFragment extends Fragment {

    public MrWallFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wall, container, false);
        WebView wvWall = (WebView) view.findViewById(R.id.wvWall);
        wvWall.loadUrl("file:///android_asset/murek.html");
        return view;
    }
}
