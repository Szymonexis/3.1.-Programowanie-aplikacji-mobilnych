package com.example.zaliczeniesub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String operation = "subtraction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showToast("Starting " + operation + " subactivity");
        Intent intent = getIntent();
        int[] data = intent.getIntArrayExtra("args");
        if(data == null) {
            showToast("No arguments");
            finish();
            return;
        }
        showToast("arguments: " + data[0] + ", " + data[1]);
        Intent result = new Intent();
        result.putExtra("operation", operation);
        result.putExtra("result", handle(data));
        setResult(Activity.RESULT_OK, result);
        finish();
    }
    private int handle(int[] data) {
        return data[0] - data[1];
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}