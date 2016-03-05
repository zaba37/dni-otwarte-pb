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
public class PowerFragment extends Fragment {

    public PowerFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_energy, container, false);
        WebView wvPower = (WebView) view.findViewById(R.id.wvPower);
        wvPower.loadUrl("file:///android_asset/energia.html");
        return view;
    }
}
