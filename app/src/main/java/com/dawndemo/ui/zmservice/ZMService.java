package com.dawndemo.ui.zmservice;

import com.dawndemo.base.UpdateService;

/**
 * Created by zc on 2017/5/16.
 */

public class ZMService extends UpdateService <ImageBean>{

    @Override
    public String getPath(ImageBean imageBean) {
        return imageBean.localPath;
    }
}
