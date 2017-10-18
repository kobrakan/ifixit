package com.example.a08760588705.welcome;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.provider.ContactsContract.RawContacts.Data;

public class ContatoActivity extends AppCompatActivity {

    private static final String[] PROJECTION =
            {
                    Data._ID,
                    Data.MIMETYPE,
                    Data.DATA1,
                    Data.DATA2,
                    Data.DATA3,
                    Data.DATA4,
                    Data.DATA5,
                    Data.DATA6,
                    Data.DATA7,
                    Data.DATA8,
                    Data.DATA9,
                    Data.DATA10,
                    Data.DATA11,
                    Data.DATA12,
                    Data.DATA13,
                    Data.DATA14,
                    Data.DATA15
            };
    private static final String SELECTION = ContactsContract.Contacts.LOOKUP_KEY + " = ?";
    private static final String ORDER_BY= Data.MIMETYPE;
    private String[] selectionArgs = {""};
    private String lookupKey;
    Cursor c = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, ORDER_BY);


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_agenda:
                    mTextMessage.setText(R.string.agenda);
                    return true;
                case R.id.navigation_atendimentos:
                    mTextMessage.setText(R.string.atendimentos);
                    return true;
                case R.id.navigation_contatos:
                    mTextMessage.setText(R.string.contatos);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

//        mTextMessage = (TextView) findViewById(R.id.txtNome);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }

}
