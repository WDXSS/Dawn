package com.dawndemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2017/10/21.
 */

public class TextWatcherActivity extends BaseActivity {
    private static final String TAG = "TextWatcherActivity";
    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.before)
    TextView mBefore;
    @BindView(R.id.changed)
    TextView mChanged;
    @BindView(R.id.after)
    TextView mAfter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_text_watcher);
        ButterKnife.bind(this);

        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mBefore.setText("start = " + start + ",count = " + count + ",after = " + after + ",s = " + s);
                Log.i(TAG, "beforeTextChanged: start = " + start + ",count = " + count + ",after = " + after + ",s = " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mChanged.setText("start = " + start + ",count = " + count + ",before = " + before + ",s = " + s);
                Log.i(TAG, "onTextChanged: start = " + start + ",count = " + count + ",before = " + before + ",s = " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mAfter.setText("s = " + s);
                Log.i(TAG, "afterTextChanged: s = " + s);
            }
        });

    }
}
