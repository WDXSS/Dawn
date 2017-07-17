package com.dawndemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
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
    private BannerView mBannerView;
    private AdmBannerView mAdMobBannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner);
        mBannerView = (BannerView) findViewById(R.id.banner);
        mAdMobBannerView = (AdmBannerView) findViewById(R.id.adm_banner);

        init();
        initAdmBanner();
    }

    private void init(){
        List<BannerBean> list = new ArrayList<>();
        BannerBean bean1 = new BannerBean();
        BannerBean bean2 = new BannerBean();
        BannerBean bean3 = new BannerBean();
        bean1.imgId = R.mipmap.img1;
        bean2.imgId = R.mipmap.img2;
        bean3.imgId = R.mipmap.img3;
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        mBannerView.setAdapter(new BannerAdapter<>(BannerActivity.this, 0 ,list));
    }
    private void initAdmBanner(){
        List<BannerBean> list = new ArrayList<>();
        BannerBean bean1 = new BannerBean();
        BannerBean bean2 = new BannerBean();
//        BannerBean bean3 = new BannerBean();
        bean1.imgId = R.mipmap.img11;
        bean2.imgId = R.mipmap.img22;
//        bean3.imgId = R.mipmap.img33;
        list.add(bean1);
        list.add(bean2);
//        list.add(bean3);
        mAdMobBannerView.setAdapter(new AdmBannerAdapter<>(BannerActivity.this, 0, list));
    }
}
