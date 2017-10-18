package com.example.a08760588705.welcome;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.a08760588705.welcome.entity.Atendimento;

public class ContatosActivity extends AppCompatActivity {


//    private static final String[] PROJECTION = {ContactsContract.Contacts._ID, ContactsContract.Contacts.LOOKUP_KEY, ContactsContract.Contacts.DISPLAY_NAME};
    private static final String[] PROJECTION =
            {

                    ContactsContract.CommonDataKinds.Phone._ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER

            };
    private static final String SELECTION = ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + " = 1 AND" +
            " "+ ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE '%CLI%' "  ;
    private static final String ORDER_BY = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC" ;
    private static final String[] FROM_COLUMNS = { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
            };
    private static final int[] TO_IDS = {R.id.txtNome, R.id.txtTelefone};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cursor mCursor ;
        setContentView(R.layout.activity_contatos);
        ListView listView = (ListView) findViewById(android.R.id.list);
        Cursor c = this.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION, SELECTION, null, ORDER_BY);
        int conta = 0;
        if (c.getCount() > 0){

            ContentValues[] cvArray = new ContentValues[c.getCount()];
            while (c.moveToNext()){
                ContentValues cv = new ContentValues();
                String id = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                cv.put("contato_id",id);
                cvArray[conta] = cv;
                conta++;
            }
            this.getContentResolver().bulkInsert(Atendimento.CONTENT_URI, cvArray);
        }
        ListAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_contato, c, FROM_COLUMNS, TO_IDS, 1 );
        listView.setAdapter(adapter);

        Button btnNovo = (Button)findViewById(R.id.btnInserir);
        btnNovo.setText(String.valueOf(conta));
        btnNovo.setOnClickListener(inserirListener);

        Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
        i.setType(ContactsContract.RawContacts.CONTENT_TYPE);

    }

    private View.OnClickListener inserirListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
            i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            startActivity(i);
        }
    };

    }
