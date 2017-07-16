package com.dawndemo.wighet.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dawndemo.R;

import java.util.List;


/**
 * Created by zc on 2017/7/16.
 */

public class BannerAdapter<T> extends PagerAdapter {

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
        FAKE_BANNER_SIZE = mList.size() + 2;
        DEFAULT_BANNER_SIZE = mList.size();
    }

    @Override
    public int getCount() {
//        return FAKE_BANNER_SIZE;
        return DEFAULT_BANNER_SIZE;
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
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mCon).inflate(R.layout.banner_item, container, false);
        Log.i(TAG, "instantiateItem: 求余前position = " +position);
        position = position % DEFAULT_BANNER_SIZE;
        ImageView img = (ImageView) view.findViewById(R.id.img);
        Log.i(TAG, "instantiateItem: 求余后position = " +position);
        int imgId = ((BannerBean) mList.get(position)).imgId;
        img.setBackgroundResource(imgId);
        container.addView(view);
        return view;
    }
}
