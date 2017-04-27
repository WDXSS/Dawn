package com.dawndemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseFragment;
import com.dawndemo.impl.EmptyBundleVIew;
import com.dawndemo.util.BundleKey;
import com.dawndemo.view.TipView;

/**
 * Created by zc on 2017/4/10.
 */

public class NavigationFragment2 extends BaseFragment {
    private static final String TAG = "NavigationFragment2";
    private String mName;
    private TextView mTvName;
    private EmptyBundleVIew mEmptyBundleVIew;
    private FrameLayout mLayoutEmpty;
    private int mUserType = 3;

    public static NavigationFragment2 newInstance(){
        NavigationFragment2 fragment2 = new NavigationFragment2();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.NAME ,NavigationFragment2.class.getSimpleName());

        fragment2.setArguments(bundle);
        return fragment2;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_na2,container,false);
        mTvName = (TextView) view.findViewById(R.id.name);
        mLayoutEmpty = (FrameLayout) view.findViewById(R.id.layout_empty);
//        mTvName.setText(mName);

//        mEmptyBundleVIew = new EmptyBundleVIew(getActivity());
//        mLayoutEmpty.addView(mEmptyBundleVIew.setText("LayoutZ中添加，带有图片 没有数据").setIcn(R.mipmap.ic_launcher).create());

        if(mUserType == 3){
//            TipView tipView = (TipView)getActivity();
//            tipView.setTipView(R.mipmap.icon_group_video,"第二个标签 ，没有数据",false);
            mEmptyBundleVIew = new EmptyBundleVIew(getActivity());
            mLayoutEmpty.addView(mEmptyBundleVIew.setText("LayoutZ中添加 没有数据").create());
        }

        return view;

    }

    private void initArguments(){

        mName =getArguments().getString(BundleKey.NAME , "");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(mUserType == 3 && hidden){
            TipView tipView = (TipView)getActivity();
            tipView.setTipView(R.mipmap.ic_launcher,"第二个标签 ，没有数据",true);
            Log.i(TAG, "onViewStateRestored: ");
        }
    }
}
