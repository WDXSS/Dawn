package com.dawndemo.ui.binding.adapter;

import android.support.annotation.Nullable;

import com.dawndemo.R;
import com.dawndemo.common.CommonAdapter;
import com.dawndemo.common.CommonViewHolder;
import com.dawndemo.ui.binding.bean.PlantBean;

import java.util.List;

/**
 * Created by zc on 2018/3/5.
 */

public class RecyclerAdapter extends CommonAdapter<PlantBean> {

    public RecyclerAdapter(@Nullable List<PlantBean> data) {
        super( R.layout.data_binding_item,data);
    }

    @Override
    protected void convert(CommonViewHolder helper, PlantBean item) {

    }
}
