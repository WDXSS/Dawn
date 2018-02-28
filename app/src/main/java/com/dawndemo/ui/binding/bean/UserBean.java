package com.dawndemo.ui.binding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.Toast;

import com.dawndemo.App;
import com.dawndemo.BR;

import java.io.Serializable;

/**
 * @Bindable
 * BR 文件
 * Created by zc on 2018/2/9.
 */

public class UserBean extends BaseObservable implements Serializable{

    private static final String TAG = "UserBean";
    @Bindable
    public String name;
    @Bindable
    public String position;

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
        userBean.setName("bean 点击 -->"+userBean.getName());
    }

    public void showName(String name){
        Log.i(TAG, "showName:  = "+ name);
        Toast.makeText(App.app, "name = "+ name ,Toast.LENGTH_SHORT).show();
    }
}
