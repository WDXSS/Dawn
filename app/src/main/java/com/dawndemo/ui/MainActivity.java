package com.dawndemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dawndemo.Bean.MainBean;
import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.base.BaseAdapter;
import com.dawndemo.base.BaseViewHolder;
import com.dawndemo.recycler.ui.RecyclerActivity;
import com.dawndemo.ui.anim.ShareElement1;
import com.dawndemo.ui.service.ServiceActivity;
import com.dawndemo.ui.zmservice.ZMActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private String s = "zc_company_zc_dev";

    private String[] titles = {"NavigationActivity", "NavigationBarActivity", "ShareElement1",
            "ServiceActivity","ZMActivity","BannerActivity","RecyclerActivity"};
    private String[] des = {"BottomNavigationView extends FramentLayout", "need to and view page  fragment ,scroll", "start Activity animal",
            "study service","测试Service 的回调", "广告位","recyclerView study"};
    private Class[] classNames = new Class[]{NavigationActivity.class, NavigationBarActivity.class, ShareElement1.class,
            ServiceActivity.class, ZMActivity.class,BannerActivity.class, RecyclerActivity.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        testBean();

        data = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            MainBean bean = new MainBean();
            bean.setTitle(titles[i]);
            bean.setDes(des[i]);

            bean.setClassName(classNames[i]);
            data.add(bean);
            initList();
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

    private void initList() {
        List<String> list = new ArrayList<>();
        List<String> local = new ArrayList<>();
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        String s6 = "6";

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);

        local.add(s1);
        local.add(s2);
        local.add(s3);

//        compare(list, local);

    }

    private void compare(List<String> list1, List<String> list2) {

        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Log.i(TAG, "compare: 外循环 i= " + i);
            for (int i1 = 0; i1 < list2.size(); i1++) {
                Log.i(TAG, "compare: 内循环 i1= " + i1);
                if (list1.get(i).equals(list2.get(i1))) {
                    newList.add(list1.get(i));
                    break;
                }
            }
        }
        list1.removeAll(newList);
        for (String s : list1) {
            Log.i(TAG, "compare: new = " + s);
        }


    }

    private void testBean() {
        List<TestBean> list = new ArrayList<>();
        List<String> local = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestBean bean = new TestBean();
            bean.name = "name " + i;
            if (i % 4 == 0) {
                bean.topRink = 1;
            }
            if (i % 2 == 0) {
                bean.sort = i;
            }
            list.add(bean);
        }
        for (TestBean testBean : list) {
            Log.i(TAG, "testBean: 赋值后的 bean.name = " + testBean.name + ",bean.topRink= " + testBean.topRink + ", bean.sort = " + testBean.sort);
        }

        Collections.sort(list, new Comparator<TestBean>() {
            @Override
            public int compare(TestBean o1, TestBean o2) {
                //降序
                if (o1.topRink < o2.topRink) {
                    return 1;
                } else if (o1.topRink == o2.topRink) {
                    if (o1.sort > o2.sort) {
                        return 1;
                    }else if(o1.sort == o2.sort){
                        return 0;
                    }
                }
                return -1;
            }
        });

        for (TestBean testBean : list) {
            Log.i(TAG, "zc_dev 分支  testBean: 排序后的 bean.name = " + testBean.name + ",bean.topRink= " + testBean.topRink + ", bean.sort = " + testBean.sort);
        }
    }

    private class TestBean {
        String name;
        int topRink;
        int sort;
    }

}
