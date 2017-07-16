package com.dawndemo.wighet.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by zc on 2017/7/16.
 */

public class BannerView extends ViewPager{
    private static final String TAG = "BannerView";

    private boolean scrollable = true;
    private int count ;
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
        addOnPageChangeListener();
    }

    private void addOnPageChangeListener(){

        this.addOnPageChangeListener(new OnPageChangeListener() {
            private int position;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                this.position = position;
                Log.i(TAG, "onPageSelected: position = "+ position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE) {
                    Log.i(TAG, "onPageScrollStateChanged:  state = "+ state + ",position = " + position );
                    if(position == 0) {
                        //要显示 viewpager的第一页：
                        setCurrentItem(1, false);
                    }
                    else if(position == count) {
                        setCurrentItem(count - 2, false);
                    }else{
                        setCurrentItem(count - 2, false);
                    }
                }
            }

        });

    }
}
