package com.example.zajecia_4;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class LifecycleActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        makeNotification("onCreate");
    }

    @Override
    protected void onStart() {
        makeNotification("onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        makeNotification("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        makeNotification("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        makeNotification("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        makeNotification("onDestroy");
        super.onDestroy();
    }

    /***NOTIFICATION METHODS***/
    String getDateTime(){
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        return String.format(Locale.getDefault(), "%04d-%02d-%02d %02d:%02d:%02d:%03d",
                year, month, day, hours, minutes, seconds,currentTimeMillis%1000);
    }

    NotificationManager notifyManager;
    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("channel_ID", getPackageName(), NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(getLocalClassName());
        notifyManager = getSystemService(NotificationManager.class);
        notifyManager.createNotificationChannel(channel);
    }

    protected void makeNotification(String methodName)
    {
        String stringTime = getDateTime();
        String stringClassName = getClass().getSimpleName();

        Notification notify = new Notification.Builder(this, "channel_ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(stringClassName)
                .setContentText(methodName + ": " + stringTime)
                .build();
        notifyManager.notify((int)System.currentTimeMillis(), notify);
    }

    protected void makeToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}