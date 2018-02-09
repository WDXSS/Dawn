package com.dawndemo.ui.binding.presenter;

import android.util.Log;

import com.dawndemo.ui.binding.bean.UserBean;

/**
 * Created by zc on 2018/2/9.
 */

public class Presenter {
    private static final String TAG = "Presenter";
    public void onClickListenerBinding(UserBean userBean) {
        //处理的点击事件
        Log.i(TAG, "onClickListenerBinding: " + userBean.getName());
    }
}
