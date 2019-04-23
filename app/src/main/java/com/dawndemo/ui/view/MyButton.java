package com.dawndemo.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Scroller;

/**
 * Created by zc on 2018/11/22
 */
@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    private static final String TAG = "MyButton" +"-- ViewMainActivity";
    private Scroller mScroller;

    public MyButton(Context context) {
        super(context);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mScroller = new Scroller(context);
    }
    @Override
    public void computeScroll() {
        Log.d(TAG, "computeScroll: mScroller.computeScrollOffset() = "+ mScroller.computeScrollOffset());
        if (mScroller.computeScrollOffset()) {
            Log.d(TAG, "computeScroll: " + mScroller.getCurrX() + ", "+mScroller.getCurrY());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }


    @Override
    public Parcelable onSaveInstanceState() {
        Log.d(TAG, "view -----onSaveInstanceState: ");
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Log.d(TAG, "view ---- onRestoreInstanceState: ");
        super.onRestoreInstanceState(state);
    }
}
