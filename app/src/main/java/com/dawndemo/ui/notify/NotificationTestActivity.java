package com.dawndemo.ui.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * 8.0的通知 26以上<br>
 * <a href="https://www.jb51.net/article/138411.htm"> 8.0的通知介绍</a> <br>
 *     https://blog.csdn.net/guolin_blog/article/details/79854070 <br>
 *
 * Created by Chao on 2018/8/13
 */
public class NotificationTestActivity extends BaseActivity {
    private String channelChat = "chat";
    private String channelSubscribe = "subscribe";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notify_test);
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //创建两个渠道
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;//重要等级
            createNotificationChannel(channelChat, channelName, importance);
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;//重要等级
            createNotificationChannel(channelSubscribe, channelName, importance);


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
        //创建渠道
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 显示未读角标
     * 创建通道：channel.setShowBadge(true);
     * 发送通知：builder.setNumber(6);
     * @param channelId
     * @param channelName
     * @param importance
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannelV2(String channelId, String channelName, int importance){
        //创建渠道
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);

        channel.setShowBadge(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 总结，8.0以上通知栏增加了，通知渠道，并不影响通知栏提醒的写法
     */
    public void lowNotification(View v){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,channelChat);
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("低版本");
        builder.setContentText("低版本 内容显示");
        assert manager != null;
        manager.notify(1, builder.build());
    }

    public void doNotify(View view) {
        //绑定渠道
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelChat);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        builder.setContentTitle("收到一条聊天消息");
        builder.setContentText("今天中午吃什么？");
        builder.setWhen(System.currentTimeMillis());
        builder .setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setNumber(6);
        assert manager != null;
        manager.notify(1, builder.build());
    }

    public void doNotifySecond(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, channelSubscribe)
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }
}
