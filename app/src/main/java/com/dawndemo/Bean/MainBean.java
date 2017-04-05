package com.dawndemo.Bean;

import android.app.Activity;

/**
 * Created by zc on 2017/3/30.
 */

public class MainBean {


    private String title;

    private String des;



    private String className;



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
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
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
