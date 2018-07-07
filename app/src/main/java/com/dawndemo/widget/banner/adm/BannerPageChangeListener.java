package com.dawndemo.widget.banner.adm;

import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * Created by zc on 2017/7/17.
 */

public class BannerPageChangeListener implements ViewPager.OnPageChangeListener {

    private static final String TAG = "BannerPageChangeListene";
    private int adapterCount;//adapter.getCount()，比数据list 大 2
    private int position;
    private PageSelectedListener pageSelectedListener;

    public BannerPageChangeListener(int count) {
        this.adapterCount = count;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        this.position = position;
        Log.i(TAG, "onPageSelected: position = " + position);
    }
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            Log.i(TAG, "onPageScrollStateChanged:  state = " + state + ",position = " + position + ",count = " + adapterCount);
            int point ;
            if (position == adapterCount - 1) {
                //要显示 viewpager的第一页：
                Log.i(TAG, "onPageScrollStateChanged: 第一张图");
                point = 1;
            } else if (position == 0) {
                Log.i(TAG, "onPageScrollStateChanged: 最后一张图");
                point = adapterCount - 2;
            } else {
                point = position;
            }
            setCurrentItem(point);
        }
    }

    //设置标记
    private void setCurrentItem(int point){
        if(pageSelectedListener != null){
            pageSelectedListener.onPageSelected(point);
        }
    }

    public void addPageSelectedListener(PageSelectedListener listener) {
        this.pageSelectedListener = listener;
    }
    /**
     * viewpager，标记
     */
    public interface PageSelectedListener {
        void onPageSelected(int position);
    }
}
