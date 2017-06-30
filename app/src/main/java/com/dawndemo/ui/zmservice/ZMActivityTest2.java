package com.dawndemo.ui.zmservice;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2017/5/15.
 */

public class ZMActivityTest2 extends BaseActivity {
    private static final String TAG = "ZMActivityTest2";
    private final String key = "987654321";
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.bind_start)
    Button mBindStart;
    @BindView(R.id.unbind_stop)
    Button mUnbindStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zm_test2);
        ButterKnife.bind(this);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.bind_start, R.id.unbind_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                bindService(bind,mConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_stop:
                unbindService(mConnection);
                break;
        }
    }
}
