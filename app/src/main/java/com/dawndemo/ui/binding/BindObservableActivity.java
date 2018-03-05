package com.dawndemo.ui.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.databinding.DataBindObservableAcBinding;
import com.dawndemo.ui.binding.bean.PlantBean;

/**
 * Observable update
 * 无需继承 BaseObservable，的绑定数据
 * Created by zc on 2018/2/27.
 */

public class BindObservableActivity extends BaseActivity{
    private static final String TAG = "BindObservableActivity";
    private DataBindObservableAcBinding mBinding;
    private PlantBean mPlantBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.data_bind_observable_ac);
        iniPlant();
        mBinding.setPlant(mPlantBean);
    }

    private void iniPlant(){
        mPlantBean = new PlantBean();
        mPlantBean.plantName.set("栀子花");
        mPlantBean.isLocal.set(false);
        mPlantBean.place.set("南方");
        mPlantBean.count.set(20);
    }

    public void onClickChange(View view){
//        mPlantBean = new PlantBean();
        mPlantBean.plantName.set("仙人球");
        mPlantBean.isLocal.set(true);
        mPlantBean.place.set("沙漠");
        mPlantBean.count.set(25);

        Log.i(TAG, "onClickChange:  mPlantBean.plantName" + mPlantBean.plantName.get());
    }
}
