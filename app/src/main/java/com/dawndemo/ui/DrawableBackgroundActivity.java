package com.dawndemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2017/9/30.
 */

public class DrawableBackgroundActivity extends BaseActivity {

    @BindView(R.id.tv_message_banner)
    TextView mTvMessageBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_drawable_bg);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_message_banner)
    public void onViewClicked() {
        mTvMessageBanner.requestFocus();
    }


}
