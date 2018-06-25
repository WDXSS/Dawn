package com.dawndemo.viewPager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by zc on 2018/6/21.
 */

public class ChildTextView extends TextView{

    private static final String TAG = "ChildTextView_zhou";
    public ChildTextView(Context context) {
        super(context);
    }

    public ChildTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean b = super.onTouchEvent(ev);
        Log.w(TAG, "onTouchEvent: " + b );

        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.w(TAG, "????????dispatchTouchEvent: " + super.dispatchTouchEvent(ev));
        return b;
    }

}
