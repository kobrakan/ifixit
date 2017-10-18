package com.example.a08760588705.welcome;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.SimpleCursorAdapter;

import com.example.a08760588705.welcome.provider.Notes;
import com.facebook.stetho.Stetho;

public class MainActivity extends ListaActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, WelcomeActivity.class);
        startActivity(i);

    }


}
