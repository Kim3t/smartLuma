package com.example.smartloca;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.textSecond);
        textView.setText("Das ist die zweite Activity");

        Button buttonOpenFirst = findViewById(R.id.buttonOpenFirst);
        buttonOpenFirst.setOnClickListener(v -> {
            // starten einer neuer Activity (in Context this starte second)
            Intent intent = new Intent( SecondActivity.this, MainActivity.class);

            // Übergibt an OS und holt aus Manifest die zweite Activity
            // -> laden von neuer Activity
            startActivity(intent);
        });
    }

}
