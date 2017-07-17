package com.dawndemo.ui.zmservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

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
    @BindView(R.id.web_view)
    WebView mWebView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zm_test2);
        ButterKnife.bind(this);

        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
//                this.setProgress(progress * 1000);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(ZMActivityTest2.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });

        WebView.setWebContentsDebuggingEnabled(true);
        mWebView.loadUrl("https://www.baidu.com/");
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

          //  unbindService(mConnection);


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
                bindService(bind, mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_stop:
                unbindService(mConnection);
                break;
        }
    }
}
