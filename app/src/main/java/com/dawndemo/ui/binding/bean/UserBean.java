package com.dawndemo.ui.binding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.dawndemo.BR;

/**
 * Created by zc on 2018/2/9.
 */

public class UserBean extends BaseObservable{

    private static final String TAG = "UserBean";

    public String name;
    public String position;
    @Bindable
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public void bindingClick(UserBean userBean){
        Log.i(TAG, "bindingClick: name -= "+ userBean.name);
    }

}
