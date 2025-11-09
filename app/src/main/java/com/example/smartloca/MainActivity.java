package com.example.smartloca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        EditText inputMessage = findViewById(R.id.inputMessage);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Wir sind schon auf Home -> nichts tun
                return true;
            } else if (id == R.id.nav_second) {
                // Input speichern
                String message = inputMessage.getText().toString().trim();

                // Default für keine eingabe
                if (message.isEmpty()){
                    // AlertDialog erfordert drücken von ok um weiter zu machen
                    new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                            .setTitle("Hinweis")
                            .setMessage("Bitte geben Sie eine Nachricht ein.")
                            .setPositiveButton("OK", null) // schließt den Dialog
                            .show();

                    // Listener hier abbrechen: kein Intent, kein Start der zweiten Activity
                    return false;
                }
                else {
                    // starten einer neuer Activity (in Context this starte second)
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    // Input übergeben an second Activity
                    // erster para -> Key für andere Activity
                    intent.putExtra("EXTRA_MESSAGE", message);

                    // Übergibt an OS und holt aus Manifest die zweite Activity
                    // -> laden von neuer Activity
                    startActivity(intent);
                    return true;
                }
            } else if (id == R.id.nav_compass) {
                //
                Intent intent = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

        // Optional: Standard ausgewähltes Item
        bottomNav.setSelectedItemId(R.id.nav_home);

    }
}
