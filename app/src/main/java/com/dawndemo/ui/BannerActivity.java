package com.dawndemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.wighet.banner.ScrollPoint;
import com.dawndemo.wighet.banner.adm.AdmBannerView;
import com.dawndemo.wighet.banner.BannerAdapter;
import com.dawndemo.wighet.banner.BannerBean;
import com.dawndemo.wighet.banner.BannerView;
import com.dawndemo.wighet.banner.adm.AdmBannerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告轮播
 * Created by zc on 2017/7/16.
 */

public class BannerActivity extends BaseActivity {
    private static final String TAG = "BannerActivity";

    private BannerView mBannerView;
    private ScrollPoint mPoint;

    private AdmBannerView mAdMobBannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner);
        //BannerView ：viewpager和标记是分开的
        mBannerView = (BannerView) findViewById(R.id.banner);
        mPoint = (ScrollPoint) findViewById(R.id.point);
        mBannerView.addPageSelectedListener(new BannerView.onPageSelectedListener() {
            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: position = " + position);
                mPoint.setSelectedIndex(position - 1);
            }
        });
        initBanner();

        //AdmBannerView 是将标记，封装在里面
        mAdMobBannerView = (AdmBannerView) findViewById(R.id.adm_banner);
        initAdmBanner();
    }

    private void initBanner() {
        List<BannerBean> list = new ArrayList<>();
        BannerBean bean1 = new BannerBean();
        BannerBean bean2 = new BannerBean();
        BannerBean bean3 = new BannerBean();
        BannerBean bean4 = new BannerBean();
        BannerBean bean5 = new BannerBean();
        BannerBean bean6 = new BannerBean();

        bean1.imgId = R.mipmap.img11;
        bean2.imgId = R.mipmap.img22;
        bean3.imgId = R.mipmap.img33;
        bean4.imgId = R.mipmap.img4;
        bean5.imgId = R.mipmap.img5;
        bean6.imgId = R.mipmap.img6;
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        mBannerView.setAdapter(new BannerAdapter<BannerBean>(BannerActivity.this, R.layout.banner_item, list) {
            @Override
            protected void convert(int position, View view, BannerBean model) {
                ImageView img = (ImageView) view.findViewById(R.id.img);
                img.setBackgroundResource(model.imgId);

            }
        });
        mPoint.setPointRes(R.drawable.new_version_point);
        mPoint.setSize(list.size());
        mBannerView.startAutoPlay();
    }

    private void initAdmBanner() {
        List<BannerBean> list = new ArrayList<>();
        BannerBean bean1 = new BannerBean();
        BannerBean bean2 = new BannerBean();
        BannerBean bean3 = new BannerBean();
        bean1.imgId = R.mipmap.img11;
        bean2.imgId = R.mipmap.img22;
        bean3.imgId = R.mipmap.img33;
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        mAdMobBannerView.setAdapter(new AdmBannerAdapter<>(BannerActivity.this, 0, list));
    }
}
