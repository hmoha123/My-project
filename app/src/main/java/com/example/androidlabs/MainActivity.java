package com.example.androidlabs;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> todos;
    private TodoAdapter adapter;

    private EditText editTextTodo;
    private Switch switchUrgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listViewTodos);
        editTextTodo = findViewById(R.id.editTextTodo);
        switchUrgent = findViewById(R.id.switchUrgent);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        todos = new ArrayList<>();
        adapter = new TodoAdapter(this, todos);
        listView.setAdapter(adapter);

        // Add button
        buttonAdd.setOnClickListener(v -> {
            String text = editTextTodo.getText().toString().trim();
            boolean urgent = switchUrgent.isChecked();

            if (TextUtils.isEmpty(text)) return;

            todos.add(new TodoItem(text, urgent));
            editTextTodo.setText("");
            switchUrgent.setChecked(false);

            adapter.notifyDataSetChanged();
        });

        // Long press to delete
        listView.setOnItemLongClickListener((parent, view, position, id) -> {

            String title = getString(R.string.delete_title);
            String msg = getString(R.string.selected_row_msg) + position;

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(title)
                    .setMessage(msg)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        todos.remove(position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss())
                    .show();

            return true;
        });
    }
}
