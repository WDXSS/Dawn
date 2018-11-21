package com.dawndemo.ui.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.ui.ipc.aidl.AidlMainActivity;
import com.dawndemo.ui.ipc.socket.TcpClientActivity;

/**
 * Created by zc on 2018/11/19
 */
public class IpcMainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_main);
    }

    public void startSocket(View view) {
        Intent intent = new Intent();
        intent.setClass(this, TcpClientActivity.class);
        startActivity(intent);
    }

    public void startAidl(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AidlMainActivity.class);
        startActivity(intent);
    }
}
