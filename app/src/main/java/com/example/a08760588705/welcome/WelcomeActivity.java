package com.example.a08760588705.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.a08760588705.welcome.provider.QuickNoteProvider;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button btnContinuar = (Button) findViewById(R.id.welcome_ok_button);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this, QuickNoteActivity.class);
                startActivity(i);
            }
        });

        Button btnContatos = (Button)findViewById(R.id.welcome_contact_button);
        btnContatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this, ContatosActivity.class);
                startActivity(i);
            }
        });

    }
}
