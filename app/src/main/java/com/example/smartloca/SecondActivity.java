package com.example.smartloca;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.nav_second) {
                // Wir sind schon auf Second -> nichts tun
                return true;
            } else if (id == R.id.nav_compass) {
                //
                Toast.makeText(this, "Compass (noch nicht implementiert)", Toast.LENGTH_SHORT).show();
                return false;
            }
            return false;
        });

        // Optional: Standard ausgewähltes Item
        bottomNav.setSelectedItemId(R.id.nav_second);


    }

}
