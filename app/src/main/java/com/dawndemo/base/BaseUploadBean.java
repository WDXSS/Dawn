package com.dawndemo.base;

import java.io.Serializable;

/**
 * Created by zc on 2017/5/16.
 */

public class BaseUploadBean implements Serializable {
    //上传状态 1没有上传，2上传成，3上传失败
    public  int uploadState;
    public String localPath;
    public String url;
}
