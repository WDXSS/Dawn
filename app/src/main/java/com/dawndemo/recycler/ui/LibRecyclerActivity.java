package com.dawndemo.recycler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.recycler.adapter.lib.LibAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用Recycler库
 * 1.显示EmptyView
 * Created by zc on 2017/7/25.
 */

public class LibRecyclerActivity extends BaseActivity{
    private RecyclerView mRecyclerView;

    private LibAdapter mAdapter;
    private List<String> mData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_lib_rec);

        initRecycler();
    }

    private void initRecycler(){
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        initAdapter();
        mData = generateData(12);
        mAdapter.setNewData(mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initAdapter(){
        mAdapter = new LibAdapter(mData);
        initEmptyView();
        initHeadView();
    }

    private void initEmptyView(){
        View view = LayoutInflater.from(LibRecyclerActivity.this).inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.setEmptyView(view);
    }

    private void initHeadView(){
        View view =  LayoutInflater.from(LibRecyclerActivity.this).inflate(R.layout.head_view, null, false);
        mAdapter.addHeaderView(view);
    }

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add("item " + i);
        }
        return data;
    }
}
