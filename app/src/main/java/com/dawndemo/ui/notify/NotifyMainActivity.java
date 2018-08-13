package com.dawndemo.ui.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;

/**
 * Created by zc on 2018/2/11.
 */

public class NotifyMainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "NotifyMainActivity";
    private final String SIMPLE_NOTIFY = "simple_notify";
    private final int MI_6_ID = 98;
    private final int SIMPLE_ID = 99;
    private final int MINE_ID = 100;


    private Button mBtnCreate;
    private Button mBtnMI6;
    private Button mBtnCustom;
    private View mBtnTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_main);
        initView();
    }

    private void initView() {
        mBtnMI6 = findViewById(R.id.btn_mi6);
        mBtnCreate = findViewById(R.id.btn_simple);
        mBtnTwo = findViewById(R.id.btn_two);
        mBtnCustom = findViewById(R.id.btn_sound);

        mBtnMI6.setOnClickListener(this);
        mBtnCreate.setOnClickListener(this);
        mBtnTwo.setOnClickListener(this);
        mBtnCustom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mi6:
                defNotify("一般写法","通知的一般写法");
                break;
            case R.id.btn_simple:
                simpleNotify(0);
                break;
            case R.id.btn_two:
                simpleNotify(1);
                simpleNotify(0);
                break;
            case R.id.btn_sound:
                simpleNotify(1);
                break;
        }
    }


    /**
     * 通常写法
     * @param title
     * @param text
     */
    private void defNotify(String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(text);
//        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        // 需要VIBRATE权限
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setPriority(Notification.PRIORITY_DEFAULT);

        // Creates an explicit intent for an Activity in your app
        //自定义打开的界面
        Intent resultIntent = new Intent(this, NotifyOpenActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(MI_6_ID, builder.build());
    }

    /**
     * 简单的通知栏
     * Builder(this, ?)
     */
    private void simpleNotify(int type ) {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotifyOpenActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, SIMPLE_NOTIFY);
        //自定义布局
//      .setContent(RemoteViews)
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("My notification");
        if(type == 0){
            builder.setContentTitle("My notification");//默认提醒方式
        }else if(type == 1){
            builder.setContentTitle("My notification -- MINE");//自定义提醒方式
        }
        builder.setContentText("Much longer text that cannot fit one line...");
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."));
        if(type == 0){
            Log.i(TAG, "simpleNotify: 默认提醒");
            addBuilderFlags(builder);//默认提醒方式
        }else if(type == 1){
            Log.i(TAG, "simpleNotify: 声音提醒");
            addMineBuilderFlags(builder);//自定义提醒方式
        }
        // Set the intent that will fire when the user taps the notification
        builder.setContentIntent(pendingIntent);

        //显示通知栏
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        Notification notification = builder.build();
        addNotificationFlags(notification);
        // notificationId is a unique int for each notification that you must define
        if(type == 0){
            notificationManager.notify(SIMPLE_ID, notification);
        }else if(type == 1){
            notificationManager.notify(MINE_ID, notification);
        }

    }

    /**
     * 只能添加在Notification中的flags
     */
    private void addNotificationFlags(Notification notification) {
        // 持续提醒直到用户响应
//        notification.flags |= Notification.FLAG_INSISTENT;
//        // 用户无法取消
//        notification.flags |= Notification.FLAG_NO_CLEAR;

    }
    /**
     * 默认提醒方式
     * @param builder
     */
    private void addBuilderFlags(NotificationCompat.Builder builder) {

//        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT); //优先级 priority
       builder.setOnlyAlertOnce(false); //设置提醒只执行一次
        builder.setDefaults(Notification.DEFAULT_SOUND);// 添加默认声音提醒
        // builder.setDefaults(Notification.DEFAULT_LIGHTS);// 添加默认呼吸灯提醒，自动添加FLAG_SHOW_LIGHTS
        builder.setDefaults(Notification.DEFAULT_VIBRATE); //单独震动
//        builder.setDefaults(Notification.DEFAULT_ALL); //声音提醒,呼吸灯提醒,震动
    }

    /**
     * 自定义提醒方式
     * @param builder
     */
    private void addMineBuilderFlags(NotificationCompat.Builder builder){
//        builder.setOnlyAlertOnce(true);//设置提醒只执行一次
//        builder.setPriority(NotificationCompat.PRIORITY_MAX); //优先级 priority

        builder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pay_001));// 添加自定义声音提醒
//        // 延迟200ms后震动300ms，再延迟400ms后震动500ms
//        long[] pattern = new long[]{200,300,400,500};// 添加自定义震动提醒
//        builder.setVibrate(pattern);
//
//        int argb = 0x00ff0000;  // led灯光颜色
//        int onMs = 300;         // led亮灯持续时间
//        int offMs = 100;        // led熄灯持续时间
//        builder.setLights(argb, onMs, offMs);// 添加自定义呼吸灯提醒，自动添加FLAG_SHOW_LIGHTS

    }

    //8.0传送门
    public void onPortal(View view) {
        startActivity(new Intent(this,NotificationTestActivity.class));
    }
}


















































