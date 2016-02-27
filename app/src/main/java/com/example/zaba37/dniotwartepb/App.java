package com.example.zaba37.dniotwartepb;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zaba3 on 27.02.2016.
 */
public class App extends Application {

    private static App applicationInstance;
    private EventBus eventBus;

    @Override
    public final void onCreate() {
        super.onCreate();

        applicationInstance = this;
        eventBus = EventBus.getDefault();
    }

    public static App getApplicationInstance() {
        return applicationInstance;
    }

    public static EventBus getEventBus() {
        return applicationInstance.eventBus;
    }
}
