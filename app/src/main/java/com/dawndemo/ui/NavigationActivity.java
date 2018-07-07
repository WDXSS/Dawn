package com.dawndemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dawndemo.Bean.TypeBean;
import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.util.ListUtil;
import com.dawndemo.widget.DropdownView;
import com.dawndemo.widget.TestLinearlayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 自定DropDownView 下拉显示
 * Created by zc on 2017/3/30.
 */

public class NavigationActivity extends BaseActivity {

    @BindView(R.id.test)
    TestLinearlayout test;
    @BindView(R.id.dropDownView)
    DropdownView dropDownView;

    private long mCurrentTypeId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        initType();
        test.setText("kaishi ----");
        dropDownView.setTitle("全部", DropdownView.ID_DROPDOWN_ONE);

        final List<TypeBean> menuList = formatTypes(list);
        dropDownView.setDate(menuList);
        dropDownView.setonSelectItemLinstener(new DropdownView.OnSelectItemListener() {
            @Override
            public void onSelected(TypeBean model) {
                Log.i("---------", "onSelected: POW 点击的回调 == ");
                dropDownView.setTitle(model.name, DropdownView.ID_DROPDOWN_ONE);
                //一级分类
                if (model.parentId == 0) {
                    dropDownView.setTitle(model.name, DropdownView.ID_DROPDOWN_ONE);
                } else {
                    //子分类
                    for (TypeBean typeBean : menuList) {
                        if (model.parentId == typeBean.id) {
                            if (model.type == 2) {
                                dropDownView.setTitle(typeBean.name, DropdownView.ID_DROPDOWN_ONE);
                            } else {
                                dropDownView.setTitle(typeBean.name + "·" + model.name, DropdownView.ID_DROPDOWN_ONE);
                            }
                            break;
                        }
                    }
                }
                mCurrentTypeId = model.id;
            }
        });
    }

    private List<TypeBean> list = new ArrayList<>();

    private void initType() {

        TypeBean bean = new TypeBean();
        bean.setName("全部");
        bean.id = 9407;
        bean.parentId = 0;
        bean.type = 2;
        bean.order = 0;
        bean.contentCount = 0;
        list.add(bean);
        TypeBean bean2 = new TypeBean();
        bean2.setName("其他");
        bean2.id = 9408;
        bean2.parentId = 0;
        bean2.type = 3;
        bean2.order = 0;
        bean2.contentCount = 1;
        list.add(bean2);
        TypeBean bean3 = new TypeBean();
        bean3.setName("1");
        bean3.id = 9761;
        bean3.parentId = 0;
        bean3.type = 1;
        bean3.order = 0;
        bean3.contentCount = 0;
        bean3.datas = new ArrayList<>();

        TypeBean bean4 = new TypeBean();
        bean4.setName("11全部");
        bean4.id = 9780;
        bean4.parentId = 9761;
        bean4.type = 2;
        bean4.order = 0;
        bean3.contentCount = 0;

        TypeBean bean5 = new TypeBean();
        bean5.setName("11其他");
        bean5.id = 9781;
        bean5.parentId = 9761;
        bean5.type = 3;
        bean5.order = 0;
        bean3.contentCount = 0;

        TypeBean bean6 = new TypeBean();
        bean6.setName("11 Hgft");
        bean6.id = 9782;
        bean6.parentId = 9761;
        bean6.type = 1;
        bean6.order = 0;
        bean3.contentCount = 0;

        bean3.datas.add(bean4);
        bean3.datas.add(bean5);
        bean3.datas.add(bean6);
        list.add(bean3);
    }

    /**
     * 如果“其他”下没有直播，就删除掉，不显示其他
     *
     * @return
     */
    public static List<TypeBean> formatTypes(List<TypeBean> list) {
        int posParentOther = -1;
        for (int i = 0; i < list.size(); i++) {
            TypeBean model = list.get(i);
            if (model.type == 3 && model.contentCount == 0) {
                posParentOther = i;
            } else if (model.type == 1 && ListUtil.isNotEmpty(model.datas)) {
                int posChildOther = -1;
                List<TypeBean> childs = model.datas;
                for (int j = 0; j < childs.size(); j++) {
                    TypeBean child = childs.get(j);
                    if (child.type == 3 && child.contentCount == 0) {
                        posChildOther = j;
                        break;
                    }
                }
                if (posChildOther >= 0) {
                    childs.remove(posChildOther);
                }
            }
        }
        if (posParentOther >= 0) {
            list.remove(posParentOther);
        }
        return list;
    }

    @OnClick({R.id.test, R.id.dropDownView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test:
                break;
            case R.id.dropDownView:
                break;
        }
    }
}
