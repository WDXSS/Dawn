package com.dawndemo.ui.zmservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2017/5/15.
 */

public class ZMActivity extends BaseActivity {
    private static final String TAG = "ZMActivity";
    @BindView(R.id.start_service)
    Button mStartService;
    @BindView(R.id.stop_service)
    Button mStopService;
    @BindView(R.id.alive)
    Button mAliveService;
    @BindView(R.id.text)
    TextView mText;

    private ServiceHelp mServiceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zm);
        ButterKnife.bind(this);
        mServiceHelper = ServiceHelp.getInstance(this);

        mServiceHelper.addServiceCallBack(new ZMService.ServiceCallBack<Integer>() {
            @Override
            public void onSucceed(List<Integer> list) {

            }

            @Override
            public void onFile(List<Integer> age1, List<Integer> age2) {

            }

            @Override
            public void onProgress(int index) {
                Log.i(TAG, "onProgress: index = " + index);
//                mText.setText(index + "");
            }
        });
    }

    @OnClick({R.id.start_service, R.id.stop_service, R.id.alive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_service:
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
}
