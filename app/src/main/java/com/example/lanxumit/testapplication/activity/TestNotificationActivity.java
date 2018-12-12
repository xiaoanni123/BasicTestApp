package com.example.lanxumit.testapplication.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lanxumit.testapplication.R;

public class TestNotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        //basic way to create Notification
        basicWay2CreateNotification();
    }

    private void basicWay2CreateNotification() {
        /**android 8.0 Notification no show problem**/

        //notification is system service   --can get by this way
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        String id = "TestChannel_01";
        String name = "TestNotification";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
            Notification.Builder builder_o = new Notification.Builder(this, id);
            builder_o.setContentTitle("Test title")
                    .setChannelId(id)
                    .setContentText("Test content")
                    .setContentIntent(getPendingIntent(Notification.FLAG_ONLY_ALERT_ONCE))//click notification need to do
                    .setWhen(System.currentTimeMillis())//notification create time normal is system time
                    .setAutoCancel(true)//auto cancel
                    .setOngoing(false)//true  is positive action
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                    .setSmallIcon(R.drawable.ic_launcher_background);
            notification = builder_o.build();
        } else {
            //instantiation notification builder
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
            //builder setting
            builder.setContentTitle("Test title")
                    .setContentText("Test content")
                    .setContentIntent(getPendingIntent(Notification.FLAG_ONLY_ALERT_ONCE))//click notification need to do
                    .setWhen(System.currentTimeMillis())//notification create time normal is system time
                    .setPriority(Notification.PRIORITY_HIGH)//notification priority
                    .setAutoCancel(true)//auto cancel
                    .setOngoing(false)//true  is positive action
                    .setSmallIcon(R.drawable.ic_launcher_background)
//                .setLargeIcon()//need a bitmap
                    //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND add voice  **** requires VIBRATE permission  ***
                    .setDefaults(Notification.DEFAULT_ALL);//add notification tips usually way
            notification = builder.build();
        }

        //end step
        notificationManager.notify(1, notification);
    }


    //get need PendIntent
    private PendingIntent getPendingIntent(int notificationFlag) {
        //Context context, int requestCode, Intent intent, Flag notificationFlag
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), notificationFlag);
        return pendingIntent;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {

    }

}
