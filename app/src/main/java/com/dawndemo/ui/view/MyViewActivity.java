package com.dawndemo.ui.view;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

public class MyViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_view);
    }
}
