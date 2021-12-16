package com.example.projekt4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void makeLayout(View view) {
        Button b = (Button) view;
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("key1", b.getText());
        startActivity(intent);
    }
}
