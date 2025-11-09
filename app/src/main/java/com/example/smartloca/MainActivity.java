package com.example.smartloca;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // start von Activity aus AndroidManifest.xml
        super.onCreate(savedInstanceState);

        // Lesen der 'res/layout/activity_main.xml'
        // -> Baut die View-Objekte (TextView, Button, ...)
        // -> nach 'setContentView()' kann in Activity auf Views zugegriffen werden (findViewById())
        setContentView(R.layout.activity_main);

        // Hier Variablen erstellen für die View-Objekte aus der XML
        // -> werden per id angesprochen (R.id.<Objekt-ID>)
        TextView helloText = findViewById(R.id.helloText);
        Button buttonChangeText = findViewById(R.id.buttonChangeText);

        // OnClickListener für den Button
        buttonChangeText.setOnClickListener(v ->
                // Ändern von der Text-View beim Button Click
                helloText.setText("Welcome to SmartLoca!")
        );

        Button buttonShowToast = findViewById(R.id.buttonShowToast);
        buttonShowToast.setOnClickListener(v ->
                // Toast ist eine nicht aufdringliche Nachricht unten auf dem Bildschirm
                // -> genutzt für kurze Rückmeldung (Formular gesendet etc.)
                Toast.makeText(
                        MainActivity.this,       // Welche Activity (falls mehrere)
                        "Button geklickt: Toast aus MainActivity", // Text
                        Toast.LENGTH_LONG               // Lenge der Toast anzeige
                ).show()
        );

        Button buttonOpenSecond = findViewById(R.id.buttonOpenSecond);
        buttonOpenSecond.setOnClickListener(v -> {
            // starten einer neuer Activity (in Context this starte second)
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            // Übergibt an OS und holt aus Manifest die zweite Activity
            // -> laden von neuer Activity
            startActivity(intent);
        });
    }
}
