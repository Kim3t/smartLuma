package com.example.smartloca;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

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

        // Intent und Extra auslesen
        String message = getIntent().getStringExtra("EXTRA_MESSAGE");

        TextView inputTextView = findViewById(R.id.inputTextSecond);
        // If - Else für eine unsichtbare TextView
        // -> nur Beispiel, da in main eingabe erzwungen wurde
        if (message != null && !message.isEmpty()) {
            inputTextView.setText(message);
            inputTextView.setVisibility(View.VISIBLE);
        } else {
            // Keine Eingabe -> TextView bleibt unsichtbar
            inputTextView.setVisibility(View.GONE);
        }
    }

}
