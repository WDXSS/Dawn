package com.dawndemo.ui.binding.bean;

import android.content.Context;
import android.content.Intent;

import com.dawndemo.ui.binding.BindObservableActivity;
import com.dawndemo.ui.binding.DataBindingActivity;

/**
 * Created by zc on 2018/2/26.
 */

public class MainBean {

    private Context mContext;

    public MainBean(Context context) {
        mContext = context;
    }

    public String getBtnText1(){
        return "to DataBindingActivity";
    }
    public void startDataBindingActivity(){
        mContext.startActivity(new Intent(mContext, DataBindingActivity.class));
    }

    public void startObservableActivity(){
        mContext.startActivity(new Intent(mContext, BindObservableActivity.class));
    }
}
