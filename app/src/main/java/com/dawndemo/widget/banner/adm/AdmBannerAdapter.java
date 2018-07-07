package com.dawndemo.widget.banner.adm;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dawndemo.R;
import com.dawndemo.widget.banner.BannerBean;

import java.util.List;


/**
 * Created by zc on 2017/7/16.
 */

public class AdmBannerAdapter<T> extends PagerAdapter {

    private static final String TAG = "BannerAdapter";
    private int FAKE_BANNER_SIZE;
    private int DEFAULT_BANNER_SIZE;

    private Context mCon;
    private int layoutId;
    private List<T> mList;

    public AdmBannerAdapter(Context con, int layoutId, List<T> list) {
        mCon = con;
        this.layoutId = layoutId;
        mList = list;
        FAKE_BANNER_SIZE = mList.size() + 2;
        DEFAULT_BANNER_SIZE = mList.size();
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
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mCon).inflate(R.layout.banner_item, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        BannerBean bannerBean ;
        int temp  = position % FAKE_BANNER_SIZE;
        if(temp == 0){
            bannerBean = (BannerBean) mList.get(DEFAULT_BANNER_SIZE -1);
        }else if(temp == FAKE_BANNER_SIZE -1){
            bannerBean = (BannerBean) mList.get(0);
        }else{
            bannerBean =  (BannerBean) mList.get(temp -1);
        }
        img.setBackgroundResource(bannerBean.imgId);
        container.addView(view);
        return view;
    }
}
