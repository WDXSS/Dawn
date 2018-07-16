package com.dawndemo.animal.base;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * 基础动画学习
 */
public class BaseAnimalActivity extends BaseActivity{

    private static final String TAG = "BaseAnimalActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_animal);
    }

    /**
     * 测试ValueAnimator <br/>
     * ValueAnimator：这个动画是针对属性的值进行动画的 ，<br/>
     * 不会对UI造成改变，不能直接实现动画效果。<br/>
     * 需要通过对动画的监听去做一些操作，<br/>
     * 在监听中将这个值设置给对应的属性，对应的属性才会改变。<br/>
     * @param view
     */
    public void addValueAnimator(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate:  animation.getAnimatedValue() = "  + animation.getAnimatedValue());
            }
        });


        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(BaseAnimalActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(BaseAnimalActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Toast.makeText(BaseAnimalActivity.this, "动画取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(BaseAnimalActivity.this, "动画重复", Toast.LENGTH_SHORT).show();
            }
        });


        //View动画的插值器
        //因为ValueAnimator的默认插值器是加速减速插值器AccelerateDecelerateInterpolator
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(3000);
        valueAnimator.start();

//        valueAnimator.setDuration();	设置动画时间
//        valueAnimator.setRepeatCount();	设置重复次数
//        valueAnimator.setInterpolator();	设置插值器
//        valueAnimator.setRepeatMode();	设置重复模式

        //ValueAnimator.ofArgb(); 设置颜色
//        valueAnimator.addListener(); 这个监听提供了start,end,cancel,repeat的监听:

    }


    public void addArgb(final View view) {
        ValueAnimator valueAnimator =  ValueAnimator.ofArgb(0xffffffff,0xffff0000,0xff0000ff);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate:  animation.getAnimatedValue() = "  + animation.getAnimatedValue());
                view.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        //View动画的插值器
        //因为ValueAnimator的默认插值器是加速减速插值器AccelerateDecelerateInterpolator
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }

    public void addXml(View view) {

        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(BaseAnimalActivity.this,R.animator.anim_test);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.i(TAG, "onAnimationUpdate:  animation.getAnimatedValue() = "  + animation.getAnimatedValue());
                }
            });
            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Toast.makeText(BaseAnimalActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Toast.makeText(BaseAnimalActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Toast.makeText(BaseAnimalActivity.this, "动画取消", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Toast.makeText(BaseAnimalActivity.this, "动画重复", Toast.LENGTH_SHORT).show();
                }
            });


    }
}
