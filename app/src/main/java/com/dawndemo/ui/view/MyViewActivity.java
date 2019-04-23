package com.dawndemo.ui.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * 测试activity异常退出后，
 * activity和view 保存数据和恢复数据的流程
 * http://ebnbin.com/2017/11/28/android-instance-state/
 */
public class MyViewActivity extends BaseActivity {
    private static final String TAG = "MyViewActivity";
    private MyEdittext myButton;
    private String str;
    StringBuffer stringBuffer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_my_view);
        myButton = findViewById(R.id.btn_0001);
        myButton.setTag(9);
        myButton.setSaveEnabled(true);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent: ");
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "Activity ---- onSaveInstanceState: ");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "Activity ---- onRestoreInstanceState: ");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.d(TAG, "Activity ----onRestoreInstanceState: 两个参数");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
