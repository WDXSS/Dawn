package com.dawndemo.ui.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试 Service
 * Created by zc on 2017/5/11.
 */

public class ServiceActivity extends BaseActivity {
    private static final String TAG = "ServiceActivity";
    @BindView(R.id.start_service)
    Button mStartService;
    @BindView(R.id.stop_service)
    Button mStopService;
    @BindView(R.id.bind_service)
    Button mBtnBindService;
    @BindView(R.id.unbind_service)
    Button mUnbindService;
    @BindView(R.id.rebind_service)
    Button mRebindService;
    @BindView(R.id.stop_thread)
    Button mStopThread;

    private Intent startService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.start_service, R.id.stop_service, R.id.bind_service, R.id.unbind_service, R.id.rebind_service,R.id.stop_thread})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                startService();
                break;
            case R.id.stop_service:
                stopService();
                break;
            case R.id.bind_service:
                bindService();
                break;
            case R.id.unbind_service:
                unbindService();
                break;
            case R.id.rebind_service:
                rebindService();
                break;
            case R.id.stop_thread:
                stopService();
                break;
        }
    }

    private void rebindService() {
        startService = new Intent();
        startService.setClass(this, BindService.class);
        startService(startService);
    }

    private void unbindService() {
        unbindService(mBindService);
    }

    private void bindService() {
        Intent intent = new Intent(this, BindService.class);
        bindService(intent, mBindService, BIND_AUTO_CREATE);
    }

    private void stopService() {
        stopService(startService);
    }

    private void startService() {
        startService = new Intent();
        startService.setClass(this, StartService.class);
        startService(startService);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    ServiceConnection mBindService = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: " + name.toString());
            BindService.MyBind bind = (BindService.MyBind) service;
            BindService bindService = bind.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: " + name.toString());
        }
    };

    private interface Call{

    }
}
