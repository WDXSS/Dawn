package com.dawndemo.widget.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by zc on 2017/7/16.
 */

public abstract class BannerAdapter<T> extends PagerAdapter {

    private static final String TAG = "BannerAdapter";
    private int FAKE_BANNER_SIZE;
    private int DEFAULT_BANNER_SIZE;

    private Context mCon;
    private int layoutId;
    private List<T> mList;

    public BannerAdapter(Context con, int layoutId, List<T> list) {
        mCon = con;
        this.layoutId = layoutId;
        mList = list;
        if(mList != null && mList.size() > 0){
            FAKE_BANNER_SIZE = mList.size() + 2;
            DEFAULT_BANNER_SIZE = mList.size();
        }

    }

    @Override
    public int getCount() {
        return FAKE_BANNER_SIZE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(mCon).inflate(layoutId, container, false);
        final int temp = position % FAKE_BANNER_SIZE;
        T t;
        if (temp == 0) {
            t = mList.get(DEFAULT_BANNER_SIZE - 1);
        } else if (temp == FAKE_BANNER_SIZE - 1) {
            t = mList.get(0);
        } else {
            t = mList.get(temp - 1);
        }
        convert(position, view, t);
        container.addView(view);
        return view;
    }
    protected abstract void convert(int position, View view, T model);

}
