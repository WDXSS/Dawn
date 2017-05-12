package com.dawndemo.ui.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by zc on 2017/5/11.
 */

public class BindService extends Service {
    private static final String TAG = "BindService";
    public static final String bindService = "bindService";
    public static final String startService = "startService";

    private Map<String, Thread> mThreads = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart: ");
    }

    @Override
//    public int onStartCommand(Intent intent, @IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true) int flags, int startId) {
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        starRun(startService);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");

        Log.i(TAG, "onDestroy:Thread.name = " + mThreads.get(startService).getName() + ", mThread.isInterrupted() = " + mThreads.get(startService).isInterrupted());
        Log.i(TAG, "onDestroy: Thread.name = " + mThreads.get(startService).getName() + ", mThread.isAlive() = " + mThreads.get(startService).isAlive());
        mThreads.get(startService).interrupt();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: ");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory: ");
    }

    /**
     *
     * @param intent
     * @return true  当Service 运行时，在再次绑定（onBind ）并且onUnbind return true 时回被调用
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        mThreads.get(bindService).interrupt();
        return super.onUnbind(intent);
//        return true;

    }

    /**
     * onReBind 当Service 启动过后，在再次绑定（onBind ）并且onUnbind return true 时回被调用
     * @param intent
     */
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind: ");
        Log.i(TAG, "onRebind: Thread.name = " + mThreads.get(bindService).getName() + ",mThread.isInterrupted() = " + mThreads.get(bindService).isInterrupted());
        Log.i(TAG, "onRebind: Thread.name = " + mThreads.get(bindService).getName() + ",mThread.isAlive() = " +mThreads.get(bindService).isAlive());

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i(TAG, "onTaskRemoved: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        starRun(bindService);
        return new MyBind();
    }

    private void starRun(final String tag){
        Thread thread = new Thread(tag){
            @Override
            public void run() {
                try {
                    int index = 0;
                    while (true){
                        sleep(2000);
                        printlnTest(index, tag);
                        index ++;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
        mThreads.put(tag,thread);

    }
    private void printlnTest(int index, String tag){
        Log.i(TAG, "printlnTest: " +  mThreads.get(tag).getName()  + ", index = " + index + ", tag= " + tag);
    }

    public class MyBind extends Binder{

          public BindService getService(){
              return BindService.this;
          }
    }


}
