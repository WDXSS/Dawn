package com.dawndemo.base;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dawndemo.ui.zmservice.ServiceCallBack;
import com.dawndemo.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试PPT上传方法
 * <p>getPath(BaseUploadBean)</p> 得到上传路径
 * Created by zc on 2017/5/15.
 */

public abstract class UpdateService<T extends BaseUploadBean> extends Service {

    private static final String TAG = "UpdateService";

    public static final String EXTRA_LIST = "extra_list";
    public static final String EXTRA_KEY = "extra_key";


    private Thread mThread;

    private static List<ServiceCallBack> mCallBacks = new ArrayList<>();
    private static boolean alive = false;
    private List<T> mData = new ArrayList<>();
    private String key;
    private Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart: ");
        this.intent = intent;
        getArguments(intent);
      //  startThread();
        alive = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: intent =" +intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        alive = false;
        if (mThread != null) {
            mThread.interrupt();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        boolean b = super.onUnbind(intent);
        Log.i(TAG, "onUnbind: " + b );
        return true;
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

    private void getArguments(Intent intent) {
        if(intent != null){
            key = intent.getStringExtra(EXTRA_KEY);
            mData = (List<T>) intent.getSerializableExtra(EXTRA_LIST);
        }
    }

    private void createThread() {
        if (!alive) {
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int index = 0;
                        while (index < mData.size()) {
                            Thread.sleep(3000);
                           boolean result = uploadPPT(index);

                            for (int i = 0; i < mCallBacks.size(); i++) {
                                T t = mData.get(index);
                                ServiceCallBack callback = mCallBacks.get(i);
                                if(result){
                                    callback.onSucceed(key, t);
                                }else{
                                    callback.onFile(key, t, 1);
                                }
                            }
                            index++;
                        }
                        for (int i = 0; i < mCallBacks.size(); i++) {
                            mCallBacks.get(i).onComplete(key, mData,0);
                        }
                        onStop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            mThread.start();
        }
    }
    private boolean uploadPPT(int index){
        Log.i(TAG, "uploadPPT: 上传ppt的方法 index = " + index);
        T t = mData.get(index);
        BaseUploadBean bean = (BaseUploadBean) t;
        if(index == 15){
            bean.uploadState = 3;
            return false;
        }else{
            bean.uploadState = 2;
            bean.url = DateUtil.longToString(System.currentTimeMillis() ) + ", url ---" + index;
        }
        return true;
    }



    public void startThread() {
        createThread();
    }

    public void onStop() {
        Log.i(TAG, "stop: 循环完毕，停止service");
        stopService(intent);
    }

    public static void addCallBack(ServiceCallBack callback) {
        mCallBacks.add(callback);
    }

    public static void remove(ServiceCallBack callback) {
        mCallBacks.remove(callback);
    }

    public static boolean getAlive() {
        Log.i(TAG, "getAlive: alive===" + alive);
        return alive;
    }


    public abstract String getPath(T t);


    public class ZMBind extends Binder {
        public UpdateService getService() {
            return UpdateService.this;
        }
    }
}
