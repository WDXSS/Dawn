package com.dawndemo.ui.zmservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dawndemo.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试PPT上传方法
 * <p>
 * Created by zc on 2017/5/15.
 */

public class ZMService extends Service {

    private static final String TAG = "ZMService";
    private Thread mThread;

    private static List<ServiceCallBack> mCallBacks = new ArrayList<>();
    private static boolean mAlive = false;



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
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        startThread();
        mAlive = true;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        mAlive = false;
        if(mThread != null){
            mThread.interrupt();
        }
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
        return new ZMBind();
    }

    /**
     * 模拟上传PPT方法
     */
    private void uploaderPPT(String path, int index) {
        try {
            Thread.sleep(3000);
            Log.i(TAG, "uploaderPPT: " + path + ", index = " + index);
            for (int i = 0; i < mCallBacks.size(); i++) {
                mCallBacks.get(i).onProgress(index);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void createThread() {
        if (mThread == null || !mThread.isAlive()) {
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int index = 0;
                    long time = System.currentTimeMillis();
                    while (index < 50) {
                        uploaderPPT(DateUtil.longToString(time) + ", index = " + index, index);
                        index++;

                    }
                    stop();
                }
            });
            mThread.start();
        }
    }


    public void startThread() {
//        createThread();
    }

    public void stop() {
        Log.i(TAG, "stop: 循环完毕，停止service");
        Intent intent = new Intent(this, ZMService.class);
        stopService(intent);
    }

    public static void addCallBack(ServiceCallBack callback) {
        mCallBacks.add(callback);
    }

    public static void remove(ServiceCallBack callback) {
        mCallBacks.remove(callback);
    }

    public static boolean getAlive() {
        Log.i(TAG, "getAlive: mAlive==="  + mAlive);
        return mAlive;
    }


    public class ZMBind extends Binder {
        public ZMService getService() {
            return ZMService.this;
        }
    }

    public interface ServiceCallBack<T> {
        void onSucceed(List<T> list);

        void onFile(List<T> age1, List<T> age2);

        void onProgress(int index);
    }
}
