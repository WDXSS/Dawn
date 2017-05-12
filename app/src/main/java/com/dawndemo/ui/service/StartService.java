package com.dawndemo.ui.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zc on 2017/5/11.
 */

public class StartService extends Service {
    private static final String TAG = "StartService";
    private Thread mThread;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate:");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        starRun();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        mThread.interrupt();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }

    private void starRun(){
        mThread = new Thread("startService"){
            @Override
            public void run() {
                try {
                   int index = 0;
                    while (true){
                        sleep(1000);
                        printlnTest(index);
                        index ++;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        mThread.start();
    }
    private void printlnTest(int index){

        Log.i(TAG, "printlnTest: " + mThread.getName() + ", index = " + index);
    }
}
