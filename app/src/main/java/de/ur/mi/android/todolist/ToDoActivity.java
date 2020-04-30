package de.ur.mi.android.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Diese App stellt die Funktionalität einer rudimentären To-Do-Liste bereit. NutzerInnen können
 * neue Aufgaben über ein Eingabefeld erstellen und zu Aufgabenliste hinzufügen.
 *
 * Die Aufgaben werden als Texte (Strings) in einer ArrayList gespeichert, die über einen Adapter
 * mit einer ListView verbunden ist. Das ListView stellt die einzelnen Aufgaben dadurch im UI dar.
 */

public class ToDoActivity extends AppCompatActivity {

    // Instanzvariablen für die referenzierten UI-Elemente
    private ListView taskList;
    private EditText taskText;

    // Instanzvariable für die Task-Liste
    private ArrayList<String> tasks;

    // Instanzvariable für den Adapter, der Task-Liste und ListView verbindet
    private ArrayAdapter<String> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initList();
    }

    private void initUI() {
        setContentView(R.layout.activity_todo);
        taskList = findViewById(R.id.taskList);
        taskText = findViewById(R.id.newTaskText);
        Button newTaskButton = findViewById(R.id.addTaskButton);
        // Für das Abfangen des Click-Events wird eine annonyme, innere Klasse verwendet. In der
        // Callback-Methode (onClick) wird eine private Methode der umschließenden Activity
        // aufgerufen (onNewTaskButton).
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewTaskButtonClicked();
            }
        });
    }

    private void initList() {
        // Erzgeugen der ArrayList zum Speichern der Aufgabentexte
        tasks = new ArrayList<>();
        // Erstellen des Adapters: Für das Layout der einzelnen Einträge wird ein vom Framework
        // vorgegebenes Layout (simple_list_item_1) verwendet. Die Darstellung innerhalb der liste
        // kann durch die Verwendung eigener Layouts angepasst werden. Als letzter Parameter wird
        // die ArrayList übergeben, die die anzuzeigenden Daten beeinhaltet.
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        // Verknüpfen des UI-Elements (ListView) mit dem erstellten Adapter
        taskList.setAdapter(taskAdapter);
    }

    // Wird bei jedem Klick auf den Button aufgerufen
    private void onNewTaskButtonClicked() {
        // Auslesen des aktuellen Texts aus dem Edit-Feld
        String text = taskText.getText().toString();
        // Prüfen, ob im EditFeld ein Text eingegeben wurde
        if(text.length() > 0) {
            // Hinzufügen des neues Tasks zur ArrayList
            tasks.add(text);
            // Adapter informiert angeschlossenen View über Änderungen am Datenbestand
            taskAdapter.notifyDataSetChanged();
        }
    }
}
