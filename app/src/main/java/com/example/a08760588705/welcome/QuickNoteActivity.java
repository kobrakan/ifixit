package com.example.a08760588705.welcome;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.a08760588705.welcome.provider.Notes;

public class QuickNoteActivity extends AppCompatActivity {
    private Cursor mCursor;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        setContentView(R.layout.activity_quick_note);


        setContentView(R.layout.activity_lista);
        Button btnInsert = (Button)findViewById(R.id.insert_button);

        btnInsert.setOnClickListener(mInsertListener);
        ListView listView = (ListView) findViewById(android.R.id.list);
        EditText editBox = (EditText) findViewById(R.id.edit_box);
        editBox.setHint("Nova nota...");
        mCursor = getContentResolver().query(Notes.CONTENT_URI, null, null, null, null);
        ListAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_item, mCursor, new String[]{Notes.TEXT}, new int[]{R.id.text});
        listView.setAdapter(adapter);


    }
    /*
     *  Definindo um OnClickListener para o botão "Inserir"
     */
    private View.OnClickListener mInsertListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText editBox = (EditText) findViewById(R.id.edit_box);
            addNote(editBox.getText().toString());
            editBox.setText("");
        }
    };

    protected void addNote(String text) {
        ContentValues values = new ContentValues();
        values.put(Notes.TEXT, text);

        getContentResolver().insert(Notes.CONTENT_URI, values);
    }

}


