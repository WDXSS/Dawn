package com.dawndemo.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import java.lang.reflect.Field;

/**
 * Created by zc on 2018/9/11
 * https://blog.csdn.net/PORSCHE_GT3RS/article/details/80304701
 */
public class AnnotationActivity extends BaseActivity{

    private TextView tvContent;
    private User mUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        tvContent = findViewById(R.id.tv);
        mUser = new User();
    }

    public void onClick1(View view) {

        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
            if(annotation!=null){
                System.out.println("property="+annotation.name());
                tvContent.setText(annotation.name());
            }
        }
    }
}
