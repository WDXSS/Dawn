package com.dawndemo.wighet.banner.adm;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.dawndemo.R;
import com.dawndemo.wighet.banner.ScrollPoint;


/**
 *
 * Created by zc on 2017/7/13.
 */

public class AdmBannerView extends RelativeLayout {
    private static final String TAG = "AdMobBannerView";
    private Context mContext;
    private BannerViewPager mViewPager;
    private ScrollPoint mScrollPoint;//banner 标记
    private int mCount;//adapter.getCount()，比数据list 大 2


    public AdmBannerView(Context context) {
        this(context, null);
    }

    public AdmBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdmBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_admob_banner, null, false);
        mViewPager = (BannerViewPager) view.findViewById(R.id.view_pager);
        mScrollPoint = (ScrollPoint) view.findViewById(R.id.scroll_point);
        addView(view);
    }

    public void setAdapter(PagerAdapter adapter) {
        mCount = adapter.getCount();
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);
        BannerPageChangeListener listener = new BannerPageChangeListener(mCount);
        listener.addPageSelectedListener(new BannerPageChangeListener.PageSelectedListener() {
            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: 当前的 position == "+ position);
                mViewPager.setCurrentItem(position,false);
                mScrollPoint.setSelectedIndex(position -1);
            }
        });
        mViewPager.addOnPageChangeListener(listener);
        setPoint();
        if(mCount <= 3){
            mViewPager.setScrollable(false);
        }
    }


    private void setPoint(){
        mScrollPoint.setPointRes(R.drawable.new_version_point);
        mScrollPoint.setSize(mCount - 2);
    }
}
