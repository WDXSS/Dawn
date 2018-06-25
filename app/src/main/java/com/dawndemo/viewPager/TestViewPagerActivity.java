package com.dawndemo.viewPager;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.EventLogTags;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/6/21.
 */

public class TestViewPagerActivity extends BaseActivity{
    private static final String TAG = "TestViewPagerActivity";
    private ViewPager mViewPager;
    private MyPagerAdapter mMyPagerAdapter;
    private List<View> mViews = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_pager);
        mViewPager = findViewById(R.id.view_pager);
        initViews(1);
        initViews(2);
        initChildViewPage();
        mMyPagerAdapter = new MyPagerAdapter();
        mViewPager.setAdapter(mMyPagerAdapter);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: " +position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged: ");
            }
        });
    }
    private void initViews(int position){
        TextView textView = new TextView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setText("第一页 ==" + position);
        mViews.add(textView);
    }




    private class MyPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View layout = (View) object;
            container.removeView(layout);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViews.get(position);
            container.addView(view);
            return view;
        }
    }


    private ChildViewPager mChildViewPager;
    private List<View> mChildViews = new ArrayList<>();
    private ChildPagerAdapter mChildPagerAdapter;
    private void initChildViewPage(){
        View view = LayoutInflater.from(this).inflate(R.layout.child_view_pager_layout,null);

        initChildViews(1);
        initChildViews(2);

        mChildViewPager = view.findViewById(R.id.child_view_pager);
        mChildPagerAdapter = new ChildPagerAdapter();
        mChildViewPager.setAdapter(mChildPagerAdapter);

        mChildViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "mChildViewPager -onPageScrolled: " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "mChildViewPager -onPageSelected: " +position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "mChildViewPager -onPageScrollStateChanged: ");
            }
        });
//        mChildViewPager.dispatchTouchEvent

        mViews.add(view);

    }
    private void initChildViews(int position){
        ChildTextView textView = new ChildTextView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setText("chilid 第一页 ==" + position);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG, "zhou--onTouch: ");
            }
        });
        mChildViews.add(textView);
    }

    private class ChildPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mChildViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View layout = (View) object;

            container.removeView(layout);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mChildViews.get(position);
            container.addView(view);
            return view;
        }
    }

}
