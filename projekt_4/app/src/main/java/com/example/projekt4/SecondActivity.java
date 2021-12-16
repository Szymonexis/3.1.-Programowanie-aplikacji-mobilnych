package com.example.projekt4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup layout = getLayout(getIntent().getStringExtra("key1"));
        if (layout == null) {
            return;
        }

        layout.addView(new ProgressBar(this));
        layout.addView(new RadioButton(this));
        layout.addView(new RatingBar(this));
        layout.addView(new RatingBar(this));
        layout.addView(new CheckBox(this));
        layout.addView(new SeekBar(this));
        setContentView(layout);
        //setContentView(R.layout.activity_second);
    }

    private ViewGroup getLayout(String message) {
        switch (message) {
            case "Frame layout": return new FrameLayout(this);
            case "Linear layout": return new LinearLayout(this);
            case "Table layout": return new TableLayout(this);
            case "Grid layout": return new GridLayout(this);
            case "Relative layout": return new RelativeLayout(this);
        }
        return null;
    }
}


