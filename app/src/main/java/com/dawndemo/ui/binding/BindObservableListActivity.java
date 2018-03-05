package com.dawndemo.ui.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.databinding.DataBindingListAcBinding;
import com.dawndemo.ui.binding.adapter.RecyclerAdapter;

/**
 * dataBinding show listView
 * Created by zc on 2018/3/5.
 */

public class BindObservableListActivity extends BaseActivity {

    private DataBindingListAcBinding mBinding;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.data_binding_list_ac);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

    }


}
