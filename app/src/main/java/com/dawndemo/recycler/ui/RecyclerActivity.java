package com.dawndemo.recycler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2017/7/25.
 */

public class RecyclerActivity extends BaseActivity implements View.OnClickListener {

    private Button mLib;
    private Button mMine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mLib = (Button) findViewById(R.id.lib);
        mMine = (Button) findViewById(R.id.mine);
        mLib.setOnClickListener(this);
        mMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lib:
                startActivity(new Intent(RecyclerActivity.this, LibRecyclerActivity.class));

                break;

            case R.id.mine:
                startActivity(new Intent(RecyclerActivity.this, RecyclerListActivity.class));
                break;
        }
    }
}
