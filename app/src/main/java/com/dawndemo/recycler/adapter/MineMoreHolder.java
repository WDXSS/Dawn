package com.dawndemo.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zc on 2017/7/24.
 */

public abstract class MineMoreHolder extends RecyclerView.ViewHolder {

    public MineMoreHolder(View itemView) {
        super(itemView);
    }
    public abstract void onBindViewHolder(MineMoreHolder holder, int position);
}
