package com.greenApp.zaba37.dzienotwartypb.event;

/**
 * Created by zaba3 on 27.02.2016.
 */
public class OnFragmentLaunchingEvent {

    int position;

    public OnFragmentLaunchingEvent(int position){
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

}
