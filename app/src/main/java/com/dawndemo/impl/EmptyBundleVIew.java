package com.dawndemo.impl;

import android.content.Context;
import android.view.View;

import com.dawndemo.base.BaseBundleView;
import com.dawndemo.wighet.EmptyLayout;

/**
 * 没有内容时显示的UI
 * Created by zc on 2017/4/10.
 */

public class EmptyBundleVIew implements BaseBundleView{
    private Context mContext;
    private EmptyLayout mEmptyLayout;


    public EmptyBundleVIew(Context context) {
       this.mContext = context;
        mEmptyLayout = new EmptyLayout(mContext);
    }

    @Override
    public BaseBundleView setIcn(int rsId) {
        mEmptyLayout.setIcn(rsId);
        return this;
    }

    @Override
    public BaseBundleView setText(String text) {
        mEmptyLayout.setText(text);
        return this;
    }
    @Override
    public BaseBundleView setView(View view) {
        mEmptyLayout.addView(mEmptyLayout);
        return this;
    }
    @Override
    public View create() {
        mEmptyLayout.initEmptyView();
       return mEmptyLayout;
    }
}
