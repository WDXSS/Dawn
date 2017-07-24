package com.dawndemo.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zc on 2017/7/24.
 */

public class MoreAdapter<T> extends RecyclerView.Adapter<MoreHolder>{
    private List<T> list;
    private Context con;

    public MoreAdapter(List<T> list, Context con) {
        this.list = list;
        this.con = con;
    }


    //Adapter 重写的方法 start
    @Override
    public MoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(MoreHolder holder, int position) {
        holder.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //Adapter 重写的方法  end

    //多布局 start
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    //多布局 end

}
