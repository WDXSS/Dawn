package com.dawndemo.ui.binding;

import android.content.Intent;
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
        //找到include 中的btn，并赋值
        mDataBinding.layout.button.setText("include显示当前值");

        mUserBean = new UserBean();
        mUserBean.name = "小明";
        mUserBean.position = "软件工程师";

        mDataBinding.setUser(mUserBean);
        mDataBinding.setPresenter(new Presenter());

        mTvName = findViewById(R.id.tv_name);
        //测试失败
        mDataBinding.setVariable(BR.name,"小VV小明");
        Log.i(TAG, "onCreate: " + mUserBean.getName());
    }

    public void test(View view){
        mUserBean.setName("点击-->小小明");
        Log.i(TAG, "test: " + mUserBean.getName() + ",text = "+ mTvName.getText());
    }
    public void toEditName(View view){
        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra("UserBean",mUserBean);
        startActivity(intent);
    }
}
