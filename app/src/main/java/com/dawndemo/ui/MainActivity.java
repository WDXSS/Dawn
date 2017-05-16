package com.dawndemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dawndemo.Bean.MainBean;
import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.base.BaseAdapter;
import com.dawndemo.base.BaseViewHolder;
import com.dawndemo.ui.anim.ShareElement1;
import com.dawndemo.ui.service.ServiceActivity;
import com.dawndemo.ui.zmservice.ZMActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * log 输出
 * sou 输出
 * 黎明前的黑暗
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recycler)
    RecyclerView recycler;


    private List<MainBean> data;
    private String TAG = "MainActivity";

    private String[] titles = {"NavigationActivity", "NavigationBarActivity", "ShareElement1",
            "ServiceActivity","ZMActivity"};
    private String[] des = {"BottomNavigationView extends FramentLayout", "need to and view page  fragment ,scroll", "start Activity animal",
            "study service","测试Service 的回调"};
    private Class[] classNames = new Class[]{NavigationActivity.class, NavigationBarActivity.class, ShareElement1.class,
            ServiceActivity.class, ZMActivity.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        data = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            MainBean bean = new MainBean();
            bean.setTitle(titles[i]);
            bean.setDes(des[i]);

            bean.setClassName(classNames[i]);
            data.add(bean);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        //设置分割线
        recycler.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        BaseAdapter<MainBean> adapter = new BaseAdapter<MainBean>(this, data, R.layout.main_item) {
            @Override
            protected void convert(BaseViewHolder holder, int position) {
                final MainBean model = data.get(position);
                ((TextView) holder.get(R.id.tv_title)).setText(model.getTitle());
                ((TextView) holder.get(R.id.tv_des)).setText(model.getDes());
                holder.get(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "text ==== " + model.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        //item设置点击事件
        adapter.setOnRecycleItemClick(new BaseAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final MainBean model = data.get(position);
                Toast.makeText(MainActivity.this, "item ==== " + model.getTitle(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, model.getClassName()));
            }
        });
        recycler.setAdapter(adapter);
    }

    @OnClick({R.id.textView, R.id.recycler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView:

                break;
            case R.id.recycler:
                break;
        }
    }

}
