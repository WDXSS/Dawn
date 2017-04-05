package com.dawndemo.wighet;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.dawndemo.Bean.TypeBean;
import com.dawndemo.R;
import com.dawndemo.util.ListUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Song on 2016/8/3.
 */
public class PopMultistageMenu extends LinearLayout {

    private int mMaxChecked = 1;
    private boolean mIsHide;
    private LayoutInflater mInflater;
    private ListView mListViewParent;
    private ListView mListViewChild;
    private MenuAdapter mParentAdapter;
    private MenuAdapter mChildAdapter;

    private List<TypeBean> mData;
    private List<TypeBean> mChildren;
    private OnMenuSelectListener mOnMenuSelectListener;

    public PopMultistageMenu(Context context) {
        this(context, null);
    }

    public PopMultistageMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopMultistageMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        View root = mInflater.inflate(R.layout.pop_multistage_menu, this);
        mListViewParent = (ListView) root.findViewById(R.id.list_parent);
        mListViewChild = (ListView) root.findViewById(R.id.list_child);
    }

    public void setParentColor(int color) {
        mListViewParent.setBackgroundColor(color);
    }

    public void setChildColor(int color) {
        mListViewChild.setBackgroundColor(color);
    }

    public void setParentSelector(int resId) {
        mListViewParent.setSelector(resId);
    }

    public void setChildSelector(int resId) {
        mListViewChild.setSelector(resId);
    }

    public void setData(List<TypeBean> data, OnMenuSelectListener l) {
        if (ListUtil.isEmpty(data)) {
            return;
        }
        this.mData = data;
        this.mOnMenuSelectListener = l;
        Pair<Integer, TypeBean> defaultChecked = getAndCheckDefault();

        mParentAdapter = new MenuAdapter(mData);
        mListViewParent.setAdapter(mParentAdapter);
        if (!mIsHide) {
            if (mMaxChecked != 1 && ListUtil.isEmpty(defaultChecked.second.datas)) {
                buildChildren(defaultChecked.second, defaultChecked.second.isCheck);
            }
            mChildren = new ArrayList<>();
            if (ListUtil.isNotEmpty(defaultChecked.second.datas)) {
                mChildren.addAll(defaultChecked.second.datas);
            }
            mChildAdapter = new MenuAdapter(mChildren);
            mListViewChild.setAdapter(mChildAdapter);
            mListViewChild.setVisibility(View.VISIBLE);
        } else {
            mListViewChild.setVisibility(View.GONE);
        }
        if (defaultChecked.first >= 0) {
            mListViewParent.setSelection(defaultChecked.first);
        }
    }

    private Pair<Integer, TypeBean> getAndCheckDefault() {
        TypeBean defaultType = null;
        int selectionPosition = -1;
        mIsHide = true;
        for (int i = 0; i < mData.size(); i++) {
            TypeBean typeBean = mData.get(i);
            // 获取第一个选中的model
            if (typeBean.isCheck && selectionPosition == -1) {
                selectionPosition = i;
                defaultType = typeBean;
            }
            // 假如有一个父类有子类的话，则显示子类列表
            if (ListUtil.isNotEmpty(typeBean.datas)) {
                mIsHide = false;
            }
        }
        // 假如没有选中的，则设置第一个为默认
        if (selectionPosition == -1) {
            defaultType = mData.get(0);
        }
        this.mLastSelectPId = defaultType.id;
        return new Pair<>(selectionPosition, defaultType);
    }

    public void notifyDataSetChanged() {
        mParentAdapter.notifyDataSetChanged();
        if (mChildAdapter != null) {
            mChildAdapter.notifyDataSetChanged();
        }
    }

    public void notifyChildSetChanged(long parentId) {
        for (TypeBean parent : mData) {
            if (parent.id == parentId) {
                mChildren.clear();
                if (ListUtil.isNotEmpty(parent.datas)) {
                    mChildren.addAll(parent.datas);
                }
                mChildAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    public void setMaxChecked(int maxChecked) {
        this.mMaxChecked = maxChecked;
    }

    /**
     * @param v
     * @param isParent true 一级分类listView     false 二级分类listView
     */
    public void addFooter(View v, boolean isParent) {
        (isParent ? mListViewParent : mListViewChild).addFooterView(v);
    }

    public void addHeader(View v, boolean isParent) {
        (isParent ? mListViewParent : mListViewChild).addHeaderView(v);
    }

    private long mLastSelectPId;

    class MenuAdapter extends BaseAdapter {
        private List<TypeBean> mTypes;

        public MenuAdapter(List<TypeBean> types) {
            this.mTypes = types;
        }

        @Override
        public int getCount() {
            return mTypes.size();
        }

        @Override
        public Object getItem(int position) {
            return mTypes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            final TypeBean typeBean = mTypes.get(position);
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_pop_multistage_menu, parent, false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.type_name);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_checked);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(typeBean.name);
            viewHolder.textView.setTextColor(typeBean.isCheck ? 0xff3c8cfa : 0xff323232);
            if (typeBean.parentId == 0) {
                convertView.setBackgroundColor(mLastSelectPId == typeBean.id ? 0xfff5f5f5 : 0xffffffff);
            }
            // 子类显示对勾；不显示子类，并且为多选时，也显示对勾
            if (mMaxChecked != 1 && (typeBean.parentId > 0 || mIsHide)) {
                viewHolder.imageView.setVisibility(typeBean.isCheck ? View.VISIBLE : View.INVISIBLE);
            } else {
                viewHolder.imageView.setVisibility(View.GONE);
            }
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    performClick(typeBean);
                }
            });
            Log.i("Adapter", "getView: id = " + typeBean.name  + ", typeBean.isCheck = " + typeBean.isCheck);
            return convertView;
        }
    }

    public void performClick(TypeBean typeBean) {
        if (typeBean.parentId == 0) {
            mLastSelectPId = typeBean.id;
            if (mMaxChecked == 1 && ListUtil.isEmpty(typeBean.datas)) {
                check(typeBean);
                PopMultistageMenu.this.notifyDataSetChanged();
            } else {
                initChildList(typeBean);
            }
        } else {
            mLastSelectPId = typeBean.parentId;
            // 选中“其他”按钮时
            if (typeBean.type == 3) {
                if (mMaxChecked == 1) {
                    checkItemOther(typeBean, true);
                    if (mOnMenuSelectListener != null) {
                        mOnMenuSelectListener.onMenuSelect(typeBean);
                    }
                } else {
                    checkItemOther(typeBean, !typeBean.isCheck);
                }
            }
            // 选中二级分类
            else {
                check(typeBean);
            }
            PopMultistageMenu.this.notifyDataSetChanged();
        }
    }

    private void initChildList(TypeBean typeBean) {
        if (typeBean.datas == null || typeBean.datas.isEmpty()) {
            if (mIsHide) {
                check(typeBean);
                PopMultistageMenu.this.notifyDataSetChanged();
                return;
            } else {
                buildChildren(typeBean, typeBean.isCheck);
            }
        }
        mChildren.clear();
        mChildren.addAll(typeBean.datas);
        PopMultistageMenu.this.notifyDataSetChanged();
    }

    private void check(TypeBean model) {
        if (mMaxChecked == 1) {
            for (TypeBean parent : mData) {
                if (parent.datas != null && !parent.datas.isEmpty()) {
                    for (TypeBean child : parent.datas) {
                        child.isCheck = model.id == child.id;
                    }
                    parent.isCheck = model.parentId == parent.id;
                } else {
                    parent.isCheck = model.id == parent.id;
                }
            }
            if (mOnMenuSelectListener != null) {
                mOnMenuSelectListener.onMenuSelect(model);
            }
        } else {
            model.isCheck = !model.isCheck;
            cancelItemOther(model.parentId);
            setParentChecked(model.parentId, model.isCheck);
        }
    }

    /**
     * 根据parentId取消选中“其他”
     */
    private void cancelItemOther(long parentId) {
        for (TypeBean parent : mData) {
            if (parent.id != parentId) {
                continue;
            }
            if (parent.datas != null && !parent.datas.isEmpty()) {
                // 因为“其他”一般在最后一位，所以这里从后面往前循环
                for (int i = parent.datas.size() - 1; i > 0; i--) {
                    if (parent.datas.get(i).type == 3) {
                        parent.datas.get(i).isCheck = false;
                        break;
                    }
                }
            }
            break;
        }
    }

    private void setParentChecked(long pid, boolean checked) {
        for (TypeBean model : mData) {
            if (model.id == pid) {
                if (!checked) {
                    for (TypeBean child : model.datas) {
                        if (child.isCheck) {
                            model.isCheck = true;
                            return;
                        }
                    }
                }
                model.isCheck = checked;
                return;
            }
        }
    }

    /**
     * 选中/取消选中其他按钮时
     */
    private void checkItemOther(TypeBean model, boolean checked) {
        model.isCheck = checked;
        for (TypeBean parent : mData) {
            if (model.parentId != parent.id) {
                continue;
            }
            if (checked) {
                for (TypeBean child : parent.datas) {
                    if (child.type != 3) {
                        child.isCheck = false;
                    }
                }
            }
            parent.isCheck = checked;
            break;
        }
    }

    private TypeBean buildChildren(TypeBean model, boolean isCheck) {
        if (model.datas == null) {
            model.datas = new ArrayList<>();
        }
        TypeBean child = new TypeBean();
        child.id = model.id;
        child.parentId = model.id;
        child.name = model.name;
        child.isCheck = isCheck;
        model.datas.add(child);
        return child;
    }

    public List<TypeBean> getChecked() {
        List<TypeBean> checked = new ArrayList<>();
        for (TypeBean model : mData) {
            if (model.datas != null && !model.datas.isEmpty()) {
                for (TypeBean child : model.datas) {
                    if (child.isCheck) {
                        checked.add(child);
                    }
                }
            } else {
                if (model.isCheck) {
                    checked.add(model);
                }
            }
        }
        return checked;
    }

    public long getLastSelectPid() {
        return mLastSelectPId;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

    public interface OnMenuSelectListener {
        void onMenuSelect(TypeBean model);
    }
}
