package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.view.View;

/**
 * Created by Administrator on 2017/5/15.
 */

public abstract class BaseAnimation {

    public static final float DEFAULT_ALPHA_FROM = 0f;

    /**
     * 半透明
     */
    public static final int ANIMATION_ALPHA=0;
    public static final int ANIMATION_SCALE_IN=1;
    public static final int ANIMATION_SCALE_IN_BOTTOM=2;
    public static final int ANIMATION_SCALE_IN_LEFT=3;
    public static final int ANIMATION_SCALE_IN_RIGHT=4;
    public static final int ANIMATION_CUSTOM=5;
    public abstract Animator[] getAnimators(View view);

    public abstract void setAnimationAlpha(float alpha);

}
