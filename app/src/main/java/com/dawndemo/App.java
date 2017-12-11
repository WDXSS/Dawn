package com.dawndemo;

import android.app.Application;
import android.util.Log;

/**
 * Created by zc on 2017/3/30.
 */

public class App extends Application {
    private static final String TAG = "App";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: company_zc");
    }
}
