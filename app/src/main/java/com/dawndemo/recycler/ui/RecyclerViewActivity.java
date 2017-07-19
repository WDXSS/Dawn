package com.dawndemo.recycler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 多布局的RecyclerView
 * Created by zc on 2017/5/30.
 */

public class RecyclerViewActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private List<String> mData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mData = generateData(50);

    }

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add("item " + i);
        }
        return data;
    }

    private class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
