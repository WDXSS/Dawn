package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Administrator on 2017/5/15.
 */

public class Animations extends BaseAnimation {

    private static final float DEFAULT_ALPHA_FROM = 0f;

    private static final float DEFAULT_SCALE_FROM = .5f;

    private  float mFrom=DEFAULT_ALPHA_FROM;

    private int animationType;

    public Animations(int animationType)
    {
        this.animationType=animationType;
        if(animationType==ANIMATION_SCALE_IN)
        {
            mFrom=DEFAULT_SCALE_FROM;
        }
    }

    @Override
    public Animator[] getAnimators(View view) {
        Animator[] return_value = null;
        switch (animationType)
        {
            case ANIMATION_ALPHA:
                return_value= new Animator[]{ObjectAnimator.ofFloat(view, "alpha", mFrom, 1f)};
                break;
            case ANIMATION_SCALE_IN:
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", mFrom, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", mFrom, 1f);
                return_value= new ObjectAnimator[] { scaleX, scaleY };
                break;
            case ANIMATION_SCALE_IN_BOTTOM:
                return_value=new Animator[]{
                        ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
                };
                break;
            case ANIMATION_SCALE_IN_LEFT:
                return_value=new Animator[] {
                        ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0)
                };
                break;
            case ANIMATION_SCALE_IN_RIGHT:
                return_value=new Animator[]{
                        ObjectAnimator.ofFloat(view, "translationX", view.getRootView().getWidth(), 0)
                };
                break;
            case ANIMATION_CUSTOM:
                return_value=new Animator[]{
                        ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f, 1),
                        ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f, 1)
                };
                break;
        }
        return return_value;
    }

    @Override
    public void setAnimationAlpha(float alpha) {
        mFrom=alpha;
    }
}
