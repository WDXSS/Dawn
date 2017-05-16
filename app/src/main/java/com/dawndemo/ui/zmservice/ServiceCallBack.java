package com.dawndemo.ui.zmservice;

import java.util.List;

/**
 *
 * Created by zc on 2017/5/16.
 */

public interface ServiceCallBack<T> {

    /**
     * @param key 标记返回，用于判断是否是同一个
     * @param t  成功的T
     */
    void onSucceed(String key, T t);
    /**
     * @param key  标记返回，用于判断是否是同一个
     * @param t 失败的T
     * @param code 失败标记码：网络，图片不存在
     */
    void onFile(String key, T t, int code);

    /**
     * 全部上传结束 ，调用此方法
     * @param key 标记返回，用于判断是否是同一个
     * @param age1  做完标记的list
     * @param code 失败标记码：网络，图片不存在
     */
    void onComplete(String key, List<T> age1, int code );
}
