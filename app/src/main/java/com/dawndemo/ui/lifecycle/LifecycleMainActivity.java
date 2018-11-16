package com.dawndemo.ui.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2018/11/15
 */
public class LifecycleMainActivity extends BaseActivity {
    private static final String TAG = "LifecycleMainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: is invoke !!!");
        setContentView(R.layout.activity_lifecycle_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart:  is invoke !!!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart:  is invoke !!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume:  is invoke !!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: is invoke !!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop:  is invoke !!!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: is invoke !!!");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent:  is invoke !!!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState:  is invoke !!!");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: is invoke !!!");
    }


    public void startSingleTopActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SingleTopActivity.class);
        startActivity(intent);
    }
}
