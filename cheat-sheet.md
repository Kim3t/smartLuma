# Android Cheat-Sheet Java/XML

| Kategorie           | Element                           | Wofür? (typisch)                                       | Merksatz zur Nutzung                                                               |
| ------------------- | --------------------------------- | ------------------------------------------------------ | ---------------------------------------------------------------------------------- |
| Struktur            | `Activity`                        | Bildschirm / Einstiegspunkt                            | Eine Activity = ein Screen. In `onCreate()` `setContentView(...)` auf dein Layout. |
| Struktur            | `Intent`                          | Zwischen Activities wechseln, andere Apps starten      | `startActivity(new Intent(this, DetailActivity.class));`                           |
| Struktur            | `Fragment`                        | Wiederverwendbare UI-Bereiche innerhalb einer Activity | Für komplexere UIs/Navigation; Activity hostet, Fragment macht die View.           |
| Layout              | `LinearLayout`                    | Einfache vertikale/horizontale Anordnung               | Schnell, übersichtlich. Für simple Screens völlig ausreichend.                     |
| Layout              | `ConstraintLayout`                | Flexible, responsive Anordnung                         | Standard für komplexere Layouts; weniger verschachtelte Views.                     |
| Basis-UI            | `TextView`                        | Text anzeigen                                          | `setText(...)` für dynamische Inhalte.                                             |
| Basis-UI            | `EditText`                        | Texteingabe (Login, Suche, Formulare)                  | Mit `getText().toString()` den Inhalt holen.                                       |
| Basis-UI            | `Button`                          | Aktionen auslösen                                      | `setOnClickListener(...)` → Logik in Activity.                                     |
| Basis-UI            | `ImageView`                       | Bilder anzeigen                                        | Für Icons, Logos, Produktbilder etc.                                               |
| Scroll              | `ScrollView`                      | Inhalte scrollbar machen                               | Eine Spalte, die zu lang ist → in `ScrollView` packen.                             |
| Listen              | `RecyclerView`                    | Listen, Feeds, Chats                                   | Standard für wiederholende Elemente; braucht Adapter (etwas Boilerplate).          |
| Feedback            | `Toast`                           | Kurze Info, unwichtig, flüchtig                        | Nur Hinweis. Kein Pflicht-lesen.                                                   |
| Feedback            | `Snackbar`                        | Kurze Info + mögliche Aktion (z. B. „Rückgängig“)      | Moderner Toast innerhalb des Layouts.                                              |
| Feedback            | `AlertDialog`                     | Bestätigungen, Warnungen, kleine Entscheidungen        | Blockiert bis Nutzer reagiert. Für „Löschen? Ja/Nein“.                             |
| System-Info         | `Notification`                    | Wichtige Ereignisse außerhalb der App                  | In Statusleiste. Für Nachrichten, Downloads, Reminder.                             |
| Daten lokal         | `SharedPreferences` / `DataStore` | Kleine Einstellungen, Flags, Login-Status              | Key-Value; „istUserEingeloggt = true“.                                             |
| Daten lokal         | `SQLite` / `Room`                 | Lokale strukturierte Daten                             | Für Listen, Caches, komplexere Datenmodelle.                                       |
| Architektur / State | `ViewModel`                       | UI-Daten über Konfig-Changes retten                    | Kein Muss am Anfang, aber Standard für saubere Trennung Logik/UI.                  |
| Hintergrund         | `WorkManager`                     | Aufgaben im Hintergrund (Sync, Uploads)                | Wenn etwas „irgendwann zuverlässig“ passieren soll.                                |

