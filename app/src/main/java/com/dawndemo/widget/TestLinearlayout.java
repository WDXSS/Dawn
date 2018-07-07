package com.dawndemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zc on 2017/4/5.
 */

public class TestLinearlayout extends LinearLayout {
    private Context con;

    public TestLinearlayout(Context context) {
        this(context ,null);
    }

    public TestLinearlayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.con = context;
        initLayout();
    }
    private void initLayout(){
        this.setOrientation(LinearLayout.HORIZONTAL);
//        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
//        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        layoutParams.height = LayoutParams.WRAP_CONTENT;
//        layoutParams.width = LayoutParams.MATCH_PARENT;
//        layoutParams.gravity = Gravity.CENTER_VERTICAL |Gravity.CENTER_HORIZONTAL;
    }

    public void setText(String text){
        TextView textView = new TextView(con);
        textView.setText(text);
//        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        textView.setLayoutParams(params);
        addView(textView);
    }














}
