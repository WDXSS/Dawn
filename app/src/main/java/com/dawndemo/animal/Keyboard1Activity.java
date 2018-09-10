package com.dawndemo.animal;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.util.keyboardutil.IPanelHeightTarget;
import com.dawndemo.util.keyboardutil.KeyboardUtil;
import com.dawndemo.util.keyboardutil.ViewUtil;

/**
 * Created by zc on 2018/6/29.
 */

public class Keyboard1Activity extends BaseActivity {
    private static final String TAG = "Keyboard1Activity";
    private ViewGroup.LayoutParams mLayoutParams;
    private View mStanceView;
    private int mPanelHeight;
    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_test_keyboard1);
        mStanceView = findViewById(R.id.panel_foot);
        mEditText = findViewById(R.id.bottom_edit);
        boolean isFull = ViewUtil.isFullScreen(this);
        Log.i(TAG, "setStanceViewHeight: isFull = " + isFull);
        //设置透明状态栏:xml中设置android:fitsSystemWindows="true"，不能设置全屏
        //单独设置FLAG_TRANSLUCENT_STATUS,输入框会被软键盘盖着
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        KeyboardUtil.attach(this, new IPanelHeightTarget() {


            @Override
            public void refreshHeight(int panelHeight) {
                mPanelHeight = panelHeight;
                Log.d(TAG, "refreshHeight: panelHeight = " + mPanelHeight);
            }

            @Override
            public int getHeight() {
                return 0;
            }

            @Override
            public void onKeyboardShowing(boolean showing) {
                Log.d(TAG, "onKeyboardShowing: showing = " + showing);
            }
        }, new KeyboardUtil.OnKeyboardShowingListener() {
            @Override
            public void onKeyboardShowing(final boolean isShowing) {
                Log.d(TAG, "onKeyboardShowing: OnKeyboardShowingListener --isShowing=== = " + isShowing);
                if (isShowing) {
//                            setStanceViewHeight(mPanelHeight);
                } else {
                    setStanceViewHeight(0);
                }

            }

        });
    }

    //设置占位View的高度
    private void setStanceViewHeight(int height) {
        boolean isFull = ViewUtil.isFullScreen(this);
        Log.i(TAG, "setStanceViewHeight: isFull = " + isFull + ",height = " + height);
        if (mLayoutParams == null) {
            mLayoutParams = mStanceView.getLayoutParams();
        }
//        if (isFull) {
//            mLayoutParams.height = height;
//            mStanceView.setLayoutParams(mLayoutParams);
//        }else{
//            mLayoutParams.height = 0;
//            mStanceView.setLayoutParams(mLayoutParams);
//        }
        mLayoutParams.height = height;
        mStanceView.setLayoutParams(mLayoutParams);
    }

    public void showKeyboard(View view) {
        setStanceViewHeight(mPanelHeight);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                KeyboardUtil.showKeyboard(mEditText);
            }
        }, 1000);

//
    }
}
