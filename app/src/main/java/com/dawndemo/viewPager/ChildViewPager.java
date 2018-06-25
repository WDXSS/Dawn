package com.dawndemo.viewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by zc on 2018/6/21.
 */

public class ChildViewPager extends ViewPager{
    private static final String TAG = "ChildViewPager_zhou";
    public ChildViewPager(Context context) {
        super(context);
    }

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean b = super.onTouchEvent(ev);
        Log.i(TAG, "onTouchEvent: " + b);
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.i(TAG, "-----dispatchTouchEvent: " + super.dispatchTouchEvent(ev));
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean b = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "?????????onInterceptTouchEvent: " + b);
        return b;
    }
}
