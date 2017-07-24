package com.dawndemo.recycler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2017/7/24.
 */

public class MoreItemActivity extends BaseActivity{

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_item);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

    }
}
