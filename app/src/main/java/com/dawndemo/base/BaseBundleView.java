package com.dawndemo.base;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zc on 2017/4/10.
 */

public interface BaseBundleView {
    BaseBundleView setIcn(int rsId);
    BaseBundleView setText(String text);
    BaseBundleView setView(View view);
    View create();
}
