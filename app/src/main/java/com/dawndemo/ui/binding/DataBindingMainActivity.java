package com.dawndemo.ui.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.databinding.DataBindingMainAtBinding;
import com.dawndemo.ui.binding.bean.MainBean;

/**
 *
 * Created by zc on 2018/2/11.
 */

public class DataBindingMainActivity extends BaseActivity {

    private DataBindingMainAtBinding mViewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.data_binding_main_at);
        MainBean mainBean = new MainBean(this);
        mViewDataBinding.setStartActivity(mainBean);
    }
}
