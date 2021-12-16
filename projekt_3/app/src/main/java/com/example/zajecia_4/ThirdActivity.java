package com.example.zajecia_4;

import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends LifecycleActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ThirdActivity prevActivity = (ThirdActivity) getLastNonConfigurationInstance();
        if (prevActivity != null) {
            this.counter = prevActivity.counter;
        }
    }

    @Override
    public void onResume() {
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(counter));
        super.onResume();
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        makeNotification("onRetainNonConfigurationInstance");
        return this;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        makeToast("onSaveInstanceState");
        makeNotification("onSaveInstanceState");
        counter++;
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        makeToast("onRestoreInstanceState");
        makeNotification("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }
}