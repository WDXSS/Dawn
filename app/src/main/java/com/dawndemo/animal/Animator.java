package com.dawndemo.animal;

/**
 * https://www.jianshu.com/p/2966227ea0b4  <br/>
 * 动画类的关系<br/>
 *  <img width="700" height="700" src="https://upload-images.jianshu.io/upload_images/2062943-db69dfe0816c37e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700" ></img>
 * Created by zc on 2018/7/11.
 */
public class Animator {
    /**
     * ValueAnimator 两子类ObjectAnimator，TimeAnimator
     */
    private class ValueAnimator extends Animator {

        private class ObjectAnimator extends ValueAnimator {
        }

        private class TimeAnimator extends ValueAnimator {
        }

    }

    private class AnimatorSet extends Animator {
    }
}
