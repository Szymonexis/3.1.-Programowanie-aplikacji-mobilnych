package com.example.zaliczenie;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) handleResult(data);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup
        setupLaunchButton();
        setupRunButton();
        setupChronometer();
    }

    private int [] gatherData() {
        EditText arg1 = findViewById(R.id.editTextFirstArgument);
        EditText arg2 = findViewById(R.id.editTextSecondArgument);

        return new int [] {
                Integer.parseInt(arg1.getText().toString()),
                Integer.parseInt(arg2.getText().toString())
        };
    }

    public void setupRunButton() {
        Button btn = findViewById(R.id.runButton);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("args", gatherData());
            intent.setAction("Zaliczenie");
            intent.setType("text/plain");
            launcher.launch(intent);
        });
    }

    private void handleResult(Intent intent) {
        TextView operationText = findViewById(R.id.textViewOperationType);
        TextView resultText = findViewById(R.id.textViewResultValue);
        operationText.setText(intent.getStringExtra("operation"));
        resultText.setText(String.valueOf(intent.getIntExtra("result", 0)));
    }

    private void setupChronometer() {
        Chronometer timer = findViewById(R.id.chronometer);
        timer.start();
    }

    private String revertString(String originalString) {
        StringBuilder revertedString = new StringBuilder(originalString).reverse();
        return revertedString.toString();
    }

    private void setupLaunchButton() {
        Button btn = findViewById(R.id.revertButton);
        btn.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.editTextRevert);
            String revertedText = editText.getText().toString();

            // Revert activity launch
            Intent intent = new Intent(MainActivity.this, RevertActivity.class);
            intent.putExtra("revertedText", revertString(revertedText));
            startActivity(intent);
        });
    }
}