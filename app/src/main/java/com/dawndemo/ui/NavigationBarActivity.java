package com.dawndemo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.ui.fragment.NavigationFragment1;
import com.dawndemo.ui.fragment.NavigationFragment2;
import com.dawndemo.ui.fragment.NavigationFragment3;
import com.dawndemo.ui.fragment.NavigationFragment4;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BottomNavigationView
 * <p>
 * ↳	android.view.View
 * ↳	android.view.ViewGroup
 * ↳	android.widget.FrameLayout
 * ↳	android.support.design.widget.BottomNavigationView
 * <p>
 * Created by zc on 2017/4/5.
 */

public class NavigationBarActivity extends BaseActivity {
    private static final String TAG = "NavigationBarActivity";

    public static final int ITME_NA1 = 0;
    public static final int ITME_NA2 = 1;
    public static final int ITME_NA3 = 2;
    public static final int ITME_NA4 = 3;

    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.bottom_na)
    BottomNavigationView mBottomNa;
    private NavigationFragment1 mNa1;
    private NavigationFragment2 mNa2;
    private NavigationFragment3 mNa3;
    private NavigationFragment4 mNa4;
    private Fragment[] mPageItems = new Fragment[4];
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_navigation_bar);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        showFragment(ITME_NA1);
        mBottomNa.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i(TAG, "onNavigationItemSelected: item " + item.getItemId());
                switch (item.getItemId()) {
                    case R.id.m1:
                        Log.i(TAG, "onNavigationItemSelected: item " + item.getTitle());
                        showFragment(ITME_NA1);
                        break;
                    case R.id.m2:
                        Log.i(TAG, "onNavigationItemSelected: item " + item.getTitle());
                        showFragment(ITME_NA2);
                        break;
                    case R.id.m3:
                        Log.i(TAG, "onNavigationItemSelected: item " + item.getTitle());
                        showFragment(ITME_NA3);
                        break;
                    case R.id.m4:
                        Log.i(TAG, "onNavigationItemSelected: item " + item.getTitle());
                        showFragment(ITME_NA4);
                        break;
                }
                return true;
            }
        });
    }

    private void showFragment(int position) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mPageItems[position];
        if (fragment == null) {
            Log.i(TAG, "setSelectItem: fragment == null");
            mPageItems [position] = getFragment(position);
            fragment = mPageItems [position];
            ft.add(R.id.container, fragment);
        }else {
            Log.i(TAG, "setSelectItem: fragment != null");
            ft.show(fragment);
        }
        for (int i = 0; i < mPageItems.length; i++) {
            if(mPageItems [i] != null && i != position){
                ft.hide(mPageItems [i]);
            }
        }
        ft.commitAllowingStateLoss();

    }

    private Fragment getFragment(int position) {
        switch (position) {
            case ITME_NA1:
                return NavigationFragment1.newInstance();
            case ITME_NA2:
                return NavigationFragment2.newInstance();
            case ITME_NA3:
                return NavigationFragment3.newInstance();
            case ITME_NA4:
                return NavigationFragment4.newInstance();
        }
       return null;
    }
}
