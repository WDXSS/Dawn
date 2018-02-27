package com.dawndemo.ui.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.databinding.DataBindingEditAcBinding;
import com.dawndemo.ui.binding.bean.UserBean;

/**
 * 1.证明传过来的Bean 是新的，修改不会影响上个activity
 * Created by zc on 2018/2/27.
 */

public class EditActivity extends BaseActivity {

    private UserBean mUserBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
        DataBindingEditAcBinding binding = DataBindingUtil.setContentView(this, R.layout.data_binding_edit_ac);
        binding.setMUser(mUserBean);
    }
    private void getArguments(){
        mUserBean = (UserBean) getIntent().getSerializableExtra("UserBean");
    }
}
