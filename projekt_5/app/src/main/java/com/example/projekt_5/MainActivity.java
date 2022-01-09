package com.example.projekt_5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                hendleResut(data);
            }
        }
    });

    private void hendleResut(Intent result) {
        TextView operationText = findViewById(R.id.operationNameTextView);
        TextView resultText = findViewById(R.id.resultNameTextView);
        operationText.setText(result.getStringExtra(Intent.EXTRA_TEXT));
        resultText.setText(String.valueOf(result.getIntExtra("result", 0)));
    }

    public void run(View view) {
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_TEXT, gatherData());
        intent.setAction("molly.projekt_5");
        intent.setType("text/plain");
        launcher.launch(intent);
    }

    private int[] gatherData() {
        TextView arg1 = findViewById(R.id.firstAttributePlainText);
        TextView arg2 = findViewById(R.id.secondAttributePlainText);
        return new int[] {
                getNumber(arg1),
                getNumber(arg2)
        };
    }

    private int getNumber(TextView tv) {
        return getNumber(tv.getText().toString());
    }

    private int getNumber(String txt) {
        return Integer.parseInt(nullCheck(txt));
    }

    private String nullCheck(String txt) {
        return txt.equals("") ? "0" : txt;
    }
}