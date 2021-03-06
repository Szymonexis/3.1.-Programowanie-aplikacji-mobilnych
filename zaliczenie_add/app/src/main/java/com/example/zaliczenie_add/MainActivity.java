package com.example.zaliczenie_add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String operation = "addition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int[] data = intent.getIntArrayExtra("args");
        if(data == null) {
            finish();
            return;
        }
        Intent result = new Intent();
        result.putExtra("operation", operation);
        result.putExtra("result", handle(data));
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    private int handle(int[] data) {
        return data[0] + data[1];
    }
}