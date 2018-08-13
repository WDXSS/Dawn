package com.dawndemo;

import android.app.Application;
import android.app.Notification;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by zc on 2017/3/30.
 */

public class App extends Application {
    private static final String TAG = "App";
    public static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Log.i(TAG, "onCreate: company_zc");
        //used in rxjava2
        AndroidNetworking.initialize(getApplicationContext());

    }
}
