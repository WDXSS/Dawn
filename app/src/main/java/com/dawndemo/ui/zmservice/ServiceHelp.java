package com.dawndemo.ui.zmservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

/**
 * Created by zc on 2017/5/15.
 */

public class ServiceHelp {

    private static final String TAG = "ServiceHelp";

    private static ServiceHelp mServiceHelp;
    private Context mContext;
    private ZMService mZmService;
    private List mData ;
    private ZMService.ServiceCallBack mCallBack;

    private ServiceConnection mConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
            ZMService.ZMBind binder = (ZMService.ZMBind
                    )service;
            mZmService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };


    private ServiceHelp(Context context) {
        this.mContext = context;
    }

    public static ServiceHelp getInstance(Context context){
        if(mServiceHelp == null ){
            mServiceHelp = new ServiceHelp(context);
        }
        return mServiceHelp;
    }


    public void setData(List data){
        this.mData = data;
    }

    public void startService(){
        Intent intent = new Intent(mContext ,ZMService.class);
        mContext.startService(intent);
        bindService();
//        mZmService.startThread();
    }

    public void stopService(){
        unbindService();
        Intent intent = new Intent(mContext ,ZMService.class);
        mContext.stopService(intent);
    }


    public void onDestroy(){
        unbindService();
        if(mCallBack != null){
            ZMService.remove(mCallBack);
        }
    }

    public void addServiceCallBack(ZMService.ServiceCallBack callBack){
        this.mCallBack = callBack;
        ZMService.addCallBack(callBack);
    }
    public static boolean isALive(){
        return ZMService.getAlive();
    }

    private void bindService(){
        Intent intent = new Intent(mContext, ZMService.class);
        mContext.bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
    }
    private void unbindService(){
        mContext.unbindService(mConnection);
    }





}
