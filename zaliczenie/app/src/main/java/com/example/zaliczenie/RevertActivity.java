package com.example.zaliczenie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RevertActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revert);

        setTextViewRevertedText();
        setupBackButton();
        createNotificationChannel();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        showNotification(getBuilder("OnCreate: " + dtf.format(now)), 0);
    }

    @Override
    protected void onDestroy() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        showNotification(getBuilder("OnDestroy: " + dtf.format(now)), 1);

        super.onDestroy();
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("channel_id", "name", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("description");
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    private void showNotification(Notification.Builder builder, Integer id) {
        notificationManager.notify(id, builder.build());
    }

    private Notification.Builder getBuilder(String text) {
        return new Notification.Builder(this, "channel_id")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("RevertActivity")
                .setContentText(text);
    }

    private void setTextViewRevertedText() {
        TextView textView = findViewById(R.id.textViewRevert);
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("revertedText");
        textView.setText(!text.equals("") ? text : "No text provided");
    }

    private void setupBackButton() {
        Button btn = findViewById(R.id.buttonBackFromRevertToMain);
        btn.setOnClickListener(v -> finish());
    }
}