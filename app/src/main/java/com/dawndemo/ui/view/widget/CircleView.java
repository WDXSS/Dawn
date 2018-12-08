package com.dawndemo.ui.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dawndemo.R;

/**
 * 1.继承自View的自定义控件，需要在onMeasure()中处理wrap_content;<br/>
 * 2. 在onDraw();方法中处理padding ；<br/>
 * 3. 自定义属性
 */
public class CircleView extends View {

    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //TODO 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        mColor = array.getColor(R.styleable.CircleView_circle_color,Color.RED);
        array.recycle();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //TODO 在onMeasure 放方法中处理wrap_content 的情况
        //得到模式
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        //得到view的width
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);

        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        //判断wrap_content 类型
        if (specWidthMode == MeasureSpec.AT_MOST && specHeightMode == MeasureSpec.AT_MOST) {
            //宽高都是wrap_content 时设置默认大小 200
            setMeasuredDimension(200, 200);
        } else if (specWidthMode == MeasureSpec.AT_MOST) {
            //宽是wrap_content
            setMeasuredDimension(200, specHeightSize);
        } else if (specHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(specWidthSize, 200);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO 去掉view的padding 值
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;
        //圆总是处于中心位置，paddingLeft 没有改变圆心位置
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
        //将圆心的位置偏移 paddingLeft 和 paddingTop
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);

    }

    private void init() {
        mPaint.setColor(mColor);
    }
}
