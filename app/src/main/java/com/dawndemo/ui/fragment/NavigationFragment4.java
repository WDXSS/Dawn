package com.dawndemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseFragment;
import com.dawndemo.util.BundleKey;

/**
 * Created by zc on 2017/4/10.
 */

public class NavigationFragment4 extends BaseFragment {
    private String mName;
    private TextView mTvName;

    public static NavigationFragment4 newInstance(){
        NavigationFragment4 fragment4 = new NavigationFragment4();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.NAME ,NavigationFragment4.class.getSimpleName());
        fragment4.setArguments(bundle);
        return fragment4;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_na4,container,false);
        mTvName = (TextView) view.findViewById(R.id.name);
        mTvName.setText(mName);
        return view;
    }

    private void initArguments(){
        mName = getArguments().getString(BundleKey.NAME , "");
    }
}
