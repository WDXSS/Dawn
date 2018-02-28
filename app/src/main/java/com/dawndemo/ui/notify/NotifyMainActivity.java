package com.dawndemo.ui.notify;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.databinding.ActivityNotifyMainBinding;
import com.dawndemo.ui.notify.bean.NotifyMainBean;

/**
 * Created by zc on 2018/2/11.
 */

public class NotifyMainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotifyMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_notify_main);
        NotifyMainBean bean = new NotifyMainBean();
        dataBinding.setNotifyMain(bean);
    }
}
