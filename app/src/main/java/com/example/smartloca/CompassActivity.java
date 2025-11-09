package com.example.smartloca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompassActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);


        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent intent = new Intent(CompassActivity.this, MainActivity.class);

                startActivity(intent);
                return true;
            } else if (id == R.id.nav_second) {
                Intent intent = new Intent(CompassActivity.this, SecondActivity.class);

                startActivity(intent);
                return true;
            } else if (id == R.id.nav_compass) {
                // Wir sind schon auf Compass -> nichts tun
                return true;
            }
            return false;
        });

        // Optional: Standard ausgewähltes Item
        bottomNav.setSelectedItemId(R.id.nav_compass);
    }
}