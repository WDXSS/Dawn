package com.dawndemo.recycler.adapter.lib;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawndemo.R;

import java.util.List;

/**
 * Created by zc on 2017/7/25.
 */

public class LibAdapter extends BaseQuickAdapter <String,BaseViewHolder>{


    public LibAdapter(@Nullable List<String> data) {
        super(R.layout.recycler_item, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
       TextView tv = helper.getView(R.id.tv_recycler);
        tv.setText(item);
    }
}
