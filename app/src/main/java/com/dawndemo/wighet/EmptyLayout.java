package com.dawndemo.wighet;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zc on 2017/4/10.
 */

public class EmptyLayout extends LinearLayout {
    private Context con;
    private String mTextStr;
    private int mRsId;
    private View mView;
    public EmptyLayout(@NonNull Context context) {
        this(context,null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.con = context;
        initView();
    }
    private void initView(){
        setOrientation(LinearLayout.VERTICAL);
    }
    public void initEmptyView(){
        if(mView != null){
            addView(mView);
        }else{
            if(!TextUtils.isEmpty(mTextStr)){
                TextView textView = new TextView(con);
                textView.setText(mTextStr);
                this.addView(textView);
            }
            if(mRsId > 0){
                ImageView img = new ImageView(con);
                img.setImageResource(mRsId);
                this.addView(img);
            }
        }
    }

    public String getText(){
        return mTextStr;
    }
    public int getRsId(){
       return mRsId;
    }
    public void setText(String text){
        this.mTextStr = text;
    }
    public void setIcn(int icnId){
        this.mRsId = icnId;
    }
    public void setView(View view){
        this.mView = view;
    }
}
