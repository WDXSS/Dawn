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

public class NavigationFragment1 extends BaseFragment {
    private static final String TAG = "NavigationFragment1";
    private String mName;
    private TextView mTvName;
    private EmptyBundleVIew mEmptyBundleVIew;
    private FrameLayout mLayoutEmpty;
    private int mUserType = 3;

    public static NavigationFragment1 newInstance(int userType){
        NavigationFragment1 fragment1 = new NavigationFragment1();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.NAME ,NavigationFragment1.class.getSimpleName());
        bundle.putInt(BundleKey.USER_TYPE, userType);
        fragment1.setArguments(bundle);
        return fragment1;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(mUserType == 3 && hidden){
            TipView tipView = (TipView)getActivity();
            tipView.setTipView(R.mipmap.icon_group_video,"LayoutZ中添加 没有数据",true);
            Log.i(TAG, "onViewStateRestored: ");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_na1,container,false);
        mTvName = (TextView) view.findViewById(R.id.name);
        mLayoutEmpty = (FrameLayout) view.findViewById(R.id.layout_empty);
//        mTvName.setText(mName);
//   mEmptyBundleVIew = new EmptyBundleVIew(getActivity());
//            mLayoutEmpty.addView(mEmptyBundleVIew.setText("LayoutZ中添加 没有数据").create());
        return view;
    }

    private void initArguments(){
        mName = getArguments().getString(BundleKey.NAME , "");
        mUserType = getArguments().getInt(BundleKey.USER_TYPE,3);
    }
}
