package com.dawndemo.animal.drag;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.widget.drag.SonnyJackDragView;

/**
 * 悬浮球效果
 * Created by zc on 2018/7/5.
 */

public class DragFloatActivity extends BaseActivity {
    private static final String TAG = "DragFloatActivity";
    private SonnyJackDragView mSonnyJackDragView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_float);

        mButton = findViewById(R.id.btn_move);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean needNearEdge = mSonnyJackDragView.getNeedNearEdge();
                mSonnyJackDragView.setNeedNearEdge(!needNearEdge);
                if (needNearEdge) {
                    mButton.setText("移至边沿");
                } else {
                    mButton.setText("固定位置");
                }
            }
        });
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DragFloatActivity.this, "点击了...", Toast.LENGTH_SHORT).show();
            }
        });
        Log.i(TAG, "onCreate: ");
        mSonnyJackDragView = new SonnyJackDragView.Builder()
                .setActivity(this)
                .setDefaultLeft(30)
                .setDefaultTop(30)
                .setNeedNearEdge(false)
                .setSize(100)
                .setView(imageView)
                .build();
    }
}
