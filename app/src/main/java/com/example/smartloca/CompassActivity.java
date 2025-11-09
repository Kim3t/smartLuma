package com.example.smartloca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private TextView textAzimuth;
    private TextView textPitch;
    private TextView textRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        textAzimuth = findViewById(R.id.textAzimuth);
        textPitch = findViewById(R.id.textPitch);
        textRoll = findViewById(R.id.textRoll);

        // Aufrufen vom Gerät SensorManager über OS
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            // laden vom Vector Sensor über sensorManager
            rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        }
        if (rotationVectorSensor == null) {
            Toast.makeText(this, "Kein Rotations-Sendor verfügbar.", Toast.LENGTH_SHORT).show();
        }



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

    @Override
    protected void onResume() {
        // Automatischer Aufruf wenn Activity im Vordergrund ist
        // -> jedes mal wenn Activity in Vordergrund wechselt
        // -> automatisch werden immer 'onCreate(), onResume() und onStart()' aufgerufen

        super.onResume();
        if (rotationVectorSensor != null && sensorManager != null) {
            // Sensor Daten von jetzt an 'this' (CompassActivity) senden
            // -> wenn etwas registriert wird, automatischer aufruf von onSensorChanged()
            sensorManager.registerListener(
                    this,                   // wohin gesendet wird
                    rotationVectorSensor,          // welcher Sensor
                    SensorManager.SENSOR_DELAY_UI  //
            );
        }
    }

    @Override
    protected void onPause() {
        // Diese Funktion wird aufgerunfen wenn Activity nicht im Fokus ist
        // -> App schließen / App verlassen / Testensperre
        // -> Andere Activity innerhalb der App öffnen z.B. zu Second wechseln

        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Wird nur aufgerufen wenn in onResume() ein sensor Registriert wird

        // if zum filtern von Sensoren welcher innerhalb verarbeitet wird
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] rotationMatrix = new float[9];
            float[] orientationAngles = new float[3];

            // getRotationMatrixFromMatrix enthält die Orientierung in einem Raum (3x3 Matrix)
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);

            // getOrientation berechnet aus den Orientierungs Daten den Winkel
            SensorManager.getOrientation(rotationMatrix, orientationAngles);

            // In Grad umrechnen
            float azimuth = (float) Math.toDegrees(orientationAngles[0]);
            float pitch = (float) Math.toDegrees(orientationAngles[1]);
            float roll = (float) Math.toDegrees(orientationAngles[2]);

            // Azimuth Normalisierung auf 0–360 (optional, sonst -180 bis +180)
            if (azimuth < 0) {
                azimuth += 360;
            }

            // Anzeigen
            textAzimuth.setText(String.format("Azimuth: %.1f°", azimuth));
            textPitch.setText(String.format("Pitch: %.1f°", pitch));
            textRoll.setText(String.format("Roll: %.1f°", roll));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Für jetzt ignorieren
    }
}