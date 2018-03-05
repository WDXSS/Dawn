package com.dawndemo.ui.binding.bean;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 *
 * Created by zc on 2018/3/5.
 */

public class PlantBean {

    public final ObservableField<String> plantName= new ObservableField<>();
    public final ObservableBoolean isLocal= new ObservableBoolean();
    public final ObservableField<String> place= new ObservableField<>();
    public final ObservableInt count= new ObservableInt();

}
