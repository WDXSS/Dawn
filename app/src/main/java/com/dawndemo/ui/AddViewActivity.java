package com.dawndemo.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2018/7/10.
 */

public class AddViewActivity extends BaseActivity{
    LinearLayout mRootView;
    LinearLayout mViewContain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_view);
        mRootView = findViewById(R.id.layout_root_view);
        mViewContain = findViewById(R.id.view_contain);

    }


    public void addTextView(View view) {
        TextView textView = new TextView(this);
        textView.setText("添加TextView");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        addView(textView);
    }

    public void addButton(View view) {
        Button textView = new Button(this);
        textView.setText("添加Button");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        int index = mViewContain.getChildCount();
        mViewContain.addView(textView,index-1);
    }

    public void addImg(View view) {
    }


    private void addView(View view){
        int index = mViewContain.getChildCount();
        mViewContain.addView(view,index);
    }
}
