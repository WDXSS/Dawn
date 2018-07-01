package com.dawndemo.animal;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.util.keyboardutil.IPanelHeightTarget;
import com.dawndemo.util.keyboardutil.KeyboardUtil;

/**
 * Created by zc on 2018/6/29.
 */

public class Keyboard1Activity extends BaseActivity {
    private static final String TAG = "Keyboard1Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_test_keyboard1);
//        //设置透明状态栏:xml中设置android:fitsSystemWindows="true"，不能设置全屏
        //单独设置FLAG_TRANSLUCENT_STATUS,输入框会被软键盘盖着
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        KeyboardUtil.attach(this, new IPanelHeightTarget() {
            @Override
            public void refreshHeight(int panelHeight) {
                Log.d(TAG, "refreshHeight: panelHeight = "+panelHeight);
            }

            @Override
            public int getHeight() {
                return 0;
            }

            @Override
            public void onKeyboardShowing(boolean showing) {
                Log.d(TAG, "onKeyboardShowing: showing = "+ showing);
            }
        },new KeyboardUtil.OnKeyboardShowingListener() {
            @Override
            public void onKeyboardShowing(boolean isShowing) {
                Log.d(TAG, "onKeyboardShowing: OnKeyboardShowingListener --isShowing=== = "+ isShowing);
            }
        });
    }
}
