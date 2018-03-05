package com.dawndemo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * CommonAdapter 使用
 * Created by zc on 2017/3/30.
 */
@Deprecated
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private int layoutId;
    private Context con;
    private List<T> data;
    private LayoutInflater inflater;
    private OnRecycleViewItemClickListener listener;

    public BaseAdapter(Context con, List<T> data, int layoutId) {

        this.layoutId = layoutId;
        this.con = con;
        this.data = data;
        inflater = LayoutInflater.from(this.con);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        convert(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.itemView, position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected abstract void convert(BaseViewHolder holder, int position);

    public void setOnRecycleItemClick(OnRecycleViewItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnRecycleViewItemClickListener {

        void onItemClick(View view, int position);
    }
}
