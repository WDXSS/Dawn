package com.dawndemo.ui.notify;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.dawndemo.base.BaseActivity;

/**
 * 8.0的通知 26以上<br>
 * <a href="https://www.jb51.net/article/138411.htm"> 8.0的通知介绍</a> <br>
 * Created by Chao on 2018/8/13
 */
public class NotificationTestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //创建两个渠道
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;//重要等级
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;//重要等级
            createNotificationChannel(channelId, channelName, importance);
        }
    }

    /**
     * 创建一个通知渠道至少需要渠道ID、渠道名称以及重要等级这三个参数，
     * 创建完成后可以在设置中找到渠道通知：1.聊天消息 2.订阅消息 （原生系统）
     * @param channelId  其中渠道ID可以随便定义，只要保证全局唯一性就可以。
     * @param channelName 渠道名称是给用户看的，需要能够表达清楚这个渠道的用途。
     * @param importance 重要等级的不同则会决定通知的不同行为，当然这里只是初始状态下的重要等级，用户可以随时手动更改某个渠道的重要等级，
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }


}
