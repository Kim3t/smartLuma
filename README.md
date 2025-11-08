# README Smart Loca 




---


## Projekt-Notiz: Umstellung von Compose auf Java/XML
Hier einmal eine Notiz über das refactor vom Projekt um es auf Java umzustellen. (ChatGPT Unterstützte umsetzung)

### Ausgangszustand

Projekt wurde ursprünglich mit Jetpack Compose Template erstellt:

* `MainActivity` als `ComponentActivity` in Kotlin
* UI via `setContent { ... }` (Compose)
* Compose-Theme (`Theme.SmartLoca`) aus `themes.xml`
* Compose-Abhängigkeiten in `build.gradle.kts`
* Keine `res/layout/activity_main.xml`

Ziel: Klassisches Setup mit

* `AppCompatActivity` in Java
* XML-Layout (`activity_main.xml`)
* Ohne Compose-UI

---

## Änderungen im Überblick

### 1. Neue MainActivity in Java

Datei: `app/src/main/java/com/example/smartloca/MainActivity.java`

Umgestellt auf klassische Activity:

```java
package com.example.smartloca;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

Wichtig:

* `AppCompatActivity` statt `ComponentActivity`
* `setContentView(R.layout.activity_main)` statt `setContent { ... }`
* Kein `import android.R;`

---

### 2. Neues XML-Layout

Pfad: `app/src/main/res/layout/activity_main.xml`

Einfaches Layout mit Text:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <TextView
        android:id="@+id/helloText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:textSize="24sp" />
</LinearLayout>
```

Damit existiert `R.layout.activity_main` überhaupt erst.

---

### 3. Manifest-Anpassung

Datei: `app/src/main/AndroidManifest.xml`

Launcher-Activity explizit auf die Java-Activity gesetzt:

```xml
<application
    android:theme="@style/Theme.SmartLoca"
    ... >

    <activity
        android:name=".MainActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>

</application>
```

Wichtig:

* Nur diese `MainActivity` hat den `MAIN`/`LAUNCHER`-Intent.
* `android:exported="true"` wegen Android 12+.

---

### 4. Theme-Anpassung

Datei: `app/src/main/res/values/themes.xml` (und ggf. `values-night/themes.xml`)

`Theme.SmartLoca` auf AppCompat-kompatibles Theme umgestellt, z. B.:

```xml
<style name="Theme.SmartLoca" parent="Theme.AppCompat.DayNight.NoActionBar">
    <!-- optionale Anpassungen -->
</style>
```

Grund:

* `AppCompatActivity` erfordert ein Theme auf Basis von `Theme.AppCompat` oder kompatibel.
* Verhindert den Crash: „You need to use a Theme.AppCompat theme…“

---

### 5. Gradle-Bereinigung (Compose raus)

Datei: `app/build.gradle.kts`

Schematisch:

* Compose-Plugin entfernt:

  * `alias(libs.plugins.kotlin.compose)` entfernt.
* `buildFeatures { compose = true }` entfernt.
* `composeOptions { ... }` entfernt.
* Alle Compose-Abhängigkeiten entfernt:

  * `androidx.activity:activity-compose`
  * `androidx.compose.*`
  * `material3` (Compose-Variante)
  * Compose Test/Tooling Dependencies

Übrig bleibt ein normales Android-App-Setup mit AppCompat/Material Views und Java-Unterstützung.

---

### 6. Kotlin-Reste

* Alte `MainActivity.kt` und `ui/theme/*.kt` sind für den aktuellen Stand nicht notwendig.
* Können gelöscht oder ignoriert werden, solange:

  * Keine zweite `MainActivity` mit gleichem Paketnamen existiert.
  * Nichts im Manifest auf die Kotlin-Activity zeigt.


