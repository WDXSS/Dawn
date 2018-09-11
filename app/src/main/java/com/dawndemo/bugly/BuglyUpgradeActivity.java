package com.dawndemo.bugly;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2018/9/10
 */
public class BuglyUpgradeActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_bugly_upgrade);
    }

    public void check1(View view) {
        BuglyUtil.checkUpgrade();
    }

    public void check2(View view) {
        BuglyUtil.checkUpgradeTest();
    }
}
