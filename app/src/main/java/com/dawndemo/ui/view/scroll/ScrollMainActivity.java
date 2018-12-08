package com.dawndemo.ui.view.scroll;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.ui.view.MyButton;

/**
 * Scroll整理 <br/>
 * 1.scrollTo和scrollBy 只是移动的内容；如：layout的scrollTo,最终移动的btn；
 * but的scrollTo，最终移动的是内容 <br/>
 * 2. 注意左右方向；从左向右滑 是负值<br/>
 * 3. getX()是相对于父类View 的坐标<br/>
 * Created by zc on 2018/11/22
 */
public class ScrollMainActivity extends BaseActivity {
    private static final String TAG = "ViewMainActivity";
    private Button mBtnStartScroll;
    private Button mButScroll;
    private LinearLayout mLayout;
    private MyButton mMyButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_main);

        mButScroll = findViewById(R.id.btn_scroll);
        mBtnStartScroll = findViewById(R.id.btn_click);
        mLayout = findViewById(R.id.layout_btn);
        mMyButton = findViewById(R.id.btn_my);
    }

    public void startScroll(View view) {
        Log.d(TAG, "startScroll: ");
        startAnimalScroll();
        //自定义button
        startMyAnimalScroll();
    }

    public void startScrollLayout(View view) {
        Log.d(TAG, "startScrollLayout: ");
        startScrollLayout();
    }

    public void isLocal(View view) {
        Log.d(TAG, "isLocal: 测试btn的 位置是否改变");
    }

    private void startAnimalScroll() {
        //总结：scrollTo和scrollBy 只是移动的内容.  btn 的内容移动
        final int deltaX = -200;
        //getX()是相对于父类View 的坐标
        final float startX = mButScroll.getX();
        Log.d(TAG, "startAnimalScroll: startX = " + startX);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                mButScroll.scrollTo((int) (startX + (deltaX * fraction)), 0);
            }
        });
        valueAnimator.start();
    }


    private void startMyAnimalScroll() {
        final int deltaX = -200;
        final float startX = mMyButton.getX();
        Log.d(TAG, "startAnimalScroll: startX = " + startX);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                mMyButton.scrollTo((int) (startX + (deltaX * fraction)), 0);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd: ");
                mMyButton.postInvalidateDelayed(1000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat: ");
            }
        });
        valueAnimator.start();
    }


    public void startScrollLayout() {
        //总结：scrollTo和scrollBy 只是移动的内容；layout的scrollTo,最终移动的btn
        // 注意左右方向；从左向右滑 是负值
        //TODO 注意赋值
        final int deltaX = -200;
        final float startX = mLayout.getX();
        Log.d(TAG, "startAnimalScroll: startX = " + startX);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                mLayout.scrollTo((int) (startX + (deltaX * fraction)), 0);
            }
        });
        valueAnimator.start();
    }
}
