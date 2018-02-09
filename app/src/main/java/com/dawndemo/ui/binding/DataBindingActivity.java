package com.dawndemo.ui.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dawndemo.BR;
import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.databinding.DataBindingAtBinding;
import com.dawndemo.ui.binding.bean.UserBean;
import com.dawndemo.ui.binding.presenter.Presenter;

/**
 * DataBinding使用
 * Created by zc on 2018/2/9.
 */

public class DataBindingActivity extends BaseActivity {
    private static final String TAG = "DataBindingActivity";
    private UserBean mUserBean;
    private TextView mTvName;
    private DataBindingAtBinding mDataBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.data_binding_at);
        mUserBean = new UserBean();
        mUserBean.name = "小明";
        mUserBean.position = "软件工程师";

        mDataBinding.setUser(mUserBean);
        mDataBinding.setPresenter(new Presenter());

        mTvName = findViewById(R.id.tv_name);
        mDataBinding.setVariable(BR.name,"小VV小明");


    }

    public void test(View view){
        mUserBean.setName("小小明");

        Log.i(TAG, "test: " + mUserBean.getName() + ",text = "+ mTvName.getText());
    }
}
