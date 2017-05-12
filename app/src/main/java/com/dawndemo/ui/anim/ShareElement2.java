package com.dawndemo.ui.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2017/3/28.
 */

public class ShareElement2 extends BaseActivity {
    @BindView(R.id.searchView)
    SearchView mSearchView;
    @BindView(R.id.back)
    Button mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.searchView, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchView:
                break;
            case R.id.back:


                break;
        }
    }
}
