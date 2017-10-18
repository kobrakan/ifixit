package com.example.a08760588705.welcome;

import android.content.Intent;
import android.os.Bundle;

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
