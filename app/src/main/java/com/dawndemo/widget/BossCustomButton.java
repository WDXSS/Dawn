package com.dawndemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawndemo.R;


public class BossCustomButton extends RelativeLayout {

    private TextView tvTitle;
    private ImageView iv_point;
    private View verticalDivider;
    private View vBottomDivider;
    private boolean checked;
    private OnToggleListener onToggleListener;
    private Context context;

    public BossCustomButton(Context context) {
        this(context,null);
        this.context = context;
    }

    public BossCustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_toggle_button, this);
        tvTitle = (TextView) findViewById(R.id.text);
        iv_point = (ImageView) findViewById(R.id.btn);
        View rlCusToggle = findViewById(R.id.rlCusToggle);
        rlCusToggle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				if (checked) {
//					checked = false;
//					setChecked(checked);
//				} else {
//					checked = true;
//					setChecked(checked);
//				}
                onToggleListener.onClick(BossCustomButton.this);
            }
        });
    }

    public void setText(String lable) {
        if (tvTitle != null) {
            this.tvTitle.setText(lable);
        }
    }

    public void setTextColor(int colors) {
        if (tvTitle != null) {
            this.tvTitle.setTextColor(colors);
        }
    }

    public String getText() {
        if (tvTitle != null) {
            return tvTitle.getText().toString();
        }
        return null;
    }

    public void setChecked(boolean isChecked) {
        this.checked = isChecked;
        if (tvTitle == null || iv_point == null) {
            return;
        }
        if (checked) {
            iv_point.setImageResource(R.mipmap.icon_arrow_down_small_selected);
        } else {
            iv_point.setImageResource(R.mipmap.icon_arrow_down_small);
        }
    }

    public boolean isChecked() {
        return checked;
    }


    public void setOnToggleListener(OnToggleListener onToggleListener) {
        this.onToggleListener = onToggleListener;
    }

    interface OnToggleListener {
        void onClick(View v);
    }
}
