package com.dawndemo.ui.zmservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.base.UpdateService;
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

    }

    @OnClick({R.id.start_service, R.id.stop_service, R.id.alive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                initServiceHelper();
                mServiceHelper.startService();
                break;
            case R.id.stop_service:
                mServiceHelper.stopService();
                break;
            case R.id.alive:
                mServiceHelper.isALive();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mServiceHelper.onDestroy();
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
