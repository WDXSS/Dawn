package com.dawndemo.common;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 *
 * <p>Created by RenTao on 2017/6/18.
 */
public abstract class CommonAdapter<T> extends BaseQuickAdapter<T, CommonViewHolder> {

    public CommonAdapter(@Nullable List<T> data) {
        super(data);
    }

    public CommonAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public void notifyItemRangeAppend(int count) {
        int start = 0;
        if (getData().size() - count > 0) {
            start = getData().size() - count;
        }
        notifyItemRangeInserted(start, count);
    }
}
