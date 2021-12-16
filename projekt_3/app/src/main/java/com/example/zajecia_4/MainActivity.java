package com.example.zajecia_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Chronometer clock = findViewById(R.id.chronometer);
        clock.start();
    }

    public void openA2(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void close(View view) {
        // typowy Javovy sposob zamykania aplikacji
//        System.exit(0);

        // Typowy Androidowy sposob zamykania aplikacji
        finishAffinity();
    }
}