package com.dawndemo.wighet.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by zc on 2017/7/16.
 */

public class BannerView extends ViewPager {
    private static final String TAG = "BannerView";

    private boolean scrollable = true;
    private int count;
    private OnPointListener mPointListener;

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        count = adapter.getCount();
        this.setCurrentItem(1);
        addOnPageChangeListener();
        setPoint(1);
    }

    private void addOnPageChangeListener() {
        this.addOnPageChangeListener(new MineOnPageChangeListener(count));

//        this.addOnPageChangeListener(new OnPageChangeListener() {
//            private int position;
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                this.position = position;
//                Log.i(TAG, "onPageSelected: position = " + position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    Log.i(TAG, "onPageScrollStateChanged:  state = " + state + ",position = " + position + ",count = " + count);
//                    if (position == count - 1) {
//                        //要显示 viewpager的第一页：
//                        Log.i(TAG, "onPageScrollStateChanged: 第一张图");
//                        setCurrentItem(1, false);
//                    } else if (position == 0) {
//                        Log.i(TAG, "onPageScrollStateChanged: 最后一张图");
//                        setCurrentItem(count - 2, false);
//                    } else {
//                        setCurrentItem(position, false);
//                    }
//                }
//            }
//
//        });

    }


    private class MineOnPageChangeListener implements OnPageChangeListener {
        private int adapterCount;//adapter.getCount()，比数据list 大 2
        private int position;

        public MineOnPageChangeListener(int count) {
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
                Log.i(TAG, "onPageScrollStateChanged:  state = " + state + ",position = " + position + ",count = " + count);
                int point ;
                if (position == adapterCount - 1) {
                    //要显示 viewpager的第一页：
                    Log.i(TAG, "onPageScrollStateChanged: 第一张图");
                    setCurrentItem(1, false);
                    point = 1;
                } else if (position == 0) {
                    Log.i(TAG, "onPageScrollStateChanged: 最后一张图");
                    setCurrentItem(adapterCount - 2, false);
                    point = adapterCount - 2;
                } else {
                    setCurrentItem(position, false);
                    point = adapterCount - 2;
                }
                setPoint(point);
            }
        }
    }

    //设置标记
    private void setPoint(int point){
        if(mPointListener != null){
            mPointListener.getPointPosition(point);
        }
    }
    public void addOnPointListener(OnPointListener listener) {
        this.mPointListener = listener;
    }
    /**
     * viewpager，标记
     */
    public interface OnPointListener {
        void getPointPosition(int position);
    }
}
