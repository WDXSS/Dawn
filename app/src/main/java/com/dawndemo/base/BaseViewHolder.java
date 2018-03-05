package com.dawndemo.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * CommonViewHolder 使用
 * Created by zc on 2017/3/30.
 */
@Deprecated
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private View view;
    public BaseViewHolder(View view) {

        super(view);
        this.view =view;
    }

    public <T extends View> T get(int id){
        T t = (T)view.findViewById(id);
        return t;
    }

}
