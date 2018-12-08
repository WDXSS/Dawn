package com.dawndemo.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.ui.view.scroll.ScrollMainActivity;

/**
 * Created by zc on 2018/11/22
 */
public class ViewMainActivity extends BaseActivity {
    private static final String TAG = "ViewMainActivity";
    private Button mBtnStartScroll;
    private Button mButScroll;
    private LinearLayout mLayout;
    private MyButton mMyButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);

        mButScroll = findViewById(R.id.btn_scroll);
        mBtnStartScroll = findViewById(R.id.btn_click);
        mLayout = findViewById(R.id.layout_btn);
        mMyButton = findViewById(R.id.btn_my);
    }

    public void startScroll(View view) {
        Intent intent = new Intent();
        intent.setClass(this,ScrollMainActivity.class);
        startActivity(intent);
    }


    public void startMyView(View view) {
        Intent intent = new Intent();
        intent.setClass(this,MyViewActivity.class);
        startActivity(intent);

    }
}
