package com.dawndemo.wighet.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ScrollPoint extends LinearLayout {
	private Context mContext;
	private LayoutParams mLayoutParams;
	private List<ImageView> mPoints = new ArrayList<ImageView>();
	/** 圆点的间距 */
	private int mPointSpacing;
	private int mPointResId;

	public ScrollPoint(Context context) {
		this(context, null);
	}

	public ScrollPoint(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContext = context;
		setOrientation(HORIZONTAL);
		// 间距默认为8dp
		mPointSpacing = 18;
	}

	public void setPointRes(int resId) {
		this.mPointResId = resId;
	}

	/**
	 * 设置点之间的间距
	 */
	public void setPointSpacing(int size) {
		this.mPointSpacing = size;
	}

	/**
	 * 设置点的总数，并且选中第一个
	 */
	public void setSize(int size) {
		setSize(size, 0);
	}

	/**
	 * 设置点的总数，并且选中第selectedIndex个
	 */
	public void setSize(int size, int selectedIndex) {
		mPoints.clear();
		removeAllViews();
		if (size <= 0) {
			return;
		}
		for (int i = 0; i < size; i++) {
			addView(buildPoint(i, selectedIndex == i));
		}
	}

	public void setSelectedIndex(int index) {
		for (int i = 0; i < mPoints.size(); i++) {
			mPoints.get(i).setSelected(index == i);
		}
	}

	private View buildPoint(int index, boolean isSelected) {
		ImageView iv = new ImageView(mContext);
		if (mLayoutParams == null) {
			mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		}
		iv.setLayoutParams(mLayoutParams);
		if (index > 0) {
			iv.setPadding(mPointSpacing, 0, 0, 0);
		}
		iv.setImageResource(mPointResId);
		iv.setSelected(isSelected);
		mPoints.add(iv);
		return iv;
	}

}
