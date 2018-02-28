package com.dawndemo.ui.binding.presenter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dawndemo.ui.binding.bean.UserBean;

/**
 *
 * Created by zc on 2018/2/9.
 */

public class Presenter {
    private static final String TAG = "Presenter";
    public void onClickListenerBinding(UserBean userBean) {
        //处理的点击事件
        Log.i(TAG, "onClickListenerBinding: " + userBean.getName());
        userBean.setName("presenter 点击 -->" +userBean.getName());
    }

    public void getName(View view, UserBean userBean){
        TextView thisView = (TextView) view;
        thisView.setText(userBean.getName());
    }

}
