package com.dawndemo.wighet.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * {@link #addPageSelectedListener(onPageSelectedListener) 显示的当前item，显示标记}
 * {@link #setScrollable(boolean) 设置是否可以滑动}
 * {@link #startAutoPlay() 开启自动轮播 @link #stopAutoPaly() 停止自动轮播}
 * Created by zc on 2017/7/16.
 */

public class BannerView extends ViewPager {
    private static final String TAG = "BannerView";

    private boolean scrollable = true;
    private boolean isScrolling = false;
    private int count;//adapter.getCount()，比数据list 大 2
    private int currentItem = 1;
    private int defCount; //viewpager,实际大小，list.size();
    private onPageSelectedListener mPointListener;
    private boolean isAutoPlay;
    private int delayTime = 3 * 1000;
    private Handler mHandler = new Handler();

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.scrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.scrollable && super.onInterceptTouchEvent(ev);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        count = adapter.getCount();
        defCount = adapter.getCount() - 2;
        if (defCount == 1) {
            setScrollable(false);
        }
        this.setCurrentItem(currentItem);
        setPageChangeListener();
        setOnSelectedChanged(currentItem);
    }

    public void startAutoPlay() {
        isAutoPlay = true;
        mHandler.removeCallbacks(task);
        mHandler.postDelayed(task, delayTime);
    }

    public void stopAutoPaly() {
        isAutoPlay = false;
        mHandler.removeCallbacks(task);
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if ((count - 2) > 1 && !isScrolling) {
                currentItem = currentItem % (count - 1) + 1;
                if (currentItem == 1) {
                    setCurrentItem(currentItem, false);
                    mHandler.post(task);
                } else {
                    setCurrentItem(currentItem);
                    mHandler.postDelayed(task, delayTime);
                }
            }
        }
    };

    private class MineOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //重新计算显示的位置
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int point;
                if (currentItem == count - 1) {
                    //要显示 viewpager的第一页：
                    point = 1;
                } else if (currentItem == 0) {
                    point = count - 2;
                } else {
                    point = currentItem;
                }
                setCurrentItem(point, false);
                setOnSelectedChanged(point);
                isScrolling = false;
                if (isAutoPlay) {
                    startAutoPlay();
                }
            }
            if (state == ViewPager.SCROLL_STATE_DRAGGING || state == ViewPager.SCROLL_STATE_SETTLING) {
                isScrolling = true;
            }
        }
    }

    private void setPageChangeListener() {
        this.addOnPageChangeListener(new MineOnPageChangeListener());
    }

    private void setOnSelectedChanged(int point) {
        if (mPointListener != null) {
            mPointListener.onPageSelected(point);
        }

    }

    public void addPageSelectedListener(onPageSelectedListener listener) {
        this.mPointListener = listener;
    }

    /**
     * viewpager，标记
     */
    public interface onPageSelectedListener {
        /**
         * @param position 是从1开始的
         */
        void onPageSelected(int position);
    }
}

