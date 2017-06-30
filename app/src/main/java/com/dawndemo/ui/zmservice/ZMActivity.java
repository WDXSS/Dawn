package com.dawndemo.ui.zmservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2017/5/15.
 */

public class ZMActivity extends BaseActivity {
    private static final String TAG = "ZMActivity";
    private final String key = "123456";
    @BindView(R.id.start_service)
    Button mStartService;
    @BindView(R.id.stop_service)
    Button mStopService;
    @BindView(R.id.alive)
    Button mAliveService;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.bind_start)
    Button mBindStart;
    @BindView(R.id.unbind_stop)
    Button mUnbindStop;

    private ServiceHelp mServiceHelper;
    private List<ImageBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zm);
        ButterKnife.bind(this);
        mServiceHelper = ServiceHelp.getInstance(this);

        mServiceHelper.addServiceCallBack(new ServiceCallBack<ImageBean>() {
            @Override
            public void onSucceed(String key, ImageBean imageBean) {
                Log.i(TAG, "onSucceed: key = " + key + ", bean.localPath= " + imageBean.localPath + ",url = " + imageBean.url);
            }

            @Override
            public void onFile(String key, ImageBean imageBean, int code) {
                Log.i(TAG, "onFile: key = " + key + ", imageBean.localPath = " + imageBean.localPath + ",url = " + imageBean.url);
            }

            @Override
            public void onComplete(String key, List<ImageBean> age1, int code) {

            }
        });
        initData();
        initServiceHelper();
    }

    @OnClick({R.id.start_service, R.id.stop_service, R.id.alive, R.id.to_zm2,R.id.btn_start, R.id.btn_stop, R.id.bind_start, R.id.unbind_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                Log.i(TAG, "onViewClicked: start_service");
                mServiceHelper.startService();
                break;
            case R.id.stop_service:
                mServiceHelper.stopService();
                break;
            case R.id.alive:
                mServiceHelper.isALive();
                break;
            case R.id.to_zm2:
                Intent intent = new Intent(this, ZMActivityTest2.class);
                startActivity(intent);
                break;
            case R.id.btn_start:
                Intent start = new Intent(this, ZMService.class);
                startService(start);
                break;
            case R.id.btn_stop:
                Intent stop = new Intent(this, ZMService.class);
                stopService(stop);
                break;
            case R.id.bind_start:
                Intent bind = new Intent(this, ZMService.class);
                bindService(bind,mServiceHelper.mConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_stop:
                unbindService(mServiceHelper.mConnection);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mServiceHelper.onDestroy();
        super.onDestroy();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            ImageBean img = new ImageBean();
            img.id = key + "---id = " + i;
            img.name = key + "--name = " + i;
            long time = System.currentTimeMillis();
            img.localPath = DateUtil.longToString(time) + "---" + i;
            img.uploadState = 1;
            mData.add(img);
        }
    }

    private void initServiceHelper() {
        mServiceHelper.setKey(key);
        mServiceHelper.setData(mData);
    }


}
