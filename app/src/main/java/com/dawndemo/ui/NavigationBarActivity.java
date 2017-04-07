package com.dawndemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * BottomNavigationView
 *
 ↳	android.view.View
 ↳	android.view.ViewGroup
 ↳	android.widget.FrameLayout
 ↳	android.support.design.widget.BottomNavigationView

 * Created by zc on 2017/4/5.
 */

public class NavigationBarActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_navigation_bar);

    }
}
