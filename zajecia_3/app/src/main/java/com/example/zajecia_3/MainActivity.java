package com.example.zajecia_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    Integer clickCounter = 0;
    private NotificationManager notificationManager;

    private static final String CHANNEL_ID = "channel_ID";
    private static final Integer CONSTANT_NOTIF_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "name",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("description");
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    private void showNotification(Notification.Builder builder) {
        notificationManager.notify(CONSTANT_NOTIF_ID, builder.build());
    }

    private Notification.Builder getBuilder(String title, String text) {
        return new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text);
    }

    public void showBasicNotification(View view) {
        showNotification(getBuilder("peepee", totalClicks()));
    }

    private String totalClicks() {
        return "Total amount of clicks so far: " + ++clickCounter;
    }

    public void showCustomNotification(View view) {
        String title = "custom_notification";
        String text = totalClicks();
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        remoteViews.setTextViewText(R.id.textView, title + text);
        remoteViews.setImageViewResource(R.id.imageView, R.drawable.ic_launcher_foreground);
        remoteViews.setImageViewResource(R.id.imageView2, R.drawable.ic_launcher_foreground);

        Notification.Builder builder = getBuilder(title, text);
        builder.setCustomContentView(remoteViews);
        showNotification(builder);
    }
}