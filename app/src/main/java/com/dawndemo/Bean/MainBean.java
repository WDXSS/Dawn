package com.dawndemo.Bean;

import android.app.Activity;

import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2017/3/30.
 */

public class MainBean {


    private String title;

    private String des;



    private Class<? extends BaseActivity> className;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    public Class<? extends BaseActivity> getClassName() {
        return className;
    }

    public void setClassName(Class<? extends BaseActivity> className) {
        this.className = className;
    }
    @Override
    public String toString() {
        return "MainBean{" +
                "title='" + title + '\'' +
                ", des='" + des + '\'' +
                ", className=" + className +
                '}';
    }
}
