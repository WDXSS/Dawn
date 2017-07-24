package com.dawndemo.recycler.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 多布局的RecyclerView
 * Created by zc on 2017/5/30.
 */

public class RecyclerListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mData = generateData(50);
        mMyAdapter = new MyAdapter(RecyclerListActivity.this,mData);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMyAdapter);

    }

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add("item " + i);
        }
        return data;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private Context con;
        private List<String> data;

        public MyAdapter(Context context, List<String> data) {
            con = context;
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(con).inflate(R.layout.recycler_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv_recycler);
            }
        }
    }

}
