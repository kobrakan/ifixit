package com.example.a08760588705.welcome.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.a08760588705.welcome.R;
import com.example.a08760588705.welcome.provider.DBHelper;


public class Fragmento2 extends Fragment {

    private FrameLayout fragmentContainer;
    public SimpleCursorAdapter scAdapter;
    public Cursor cursor;
    public ListView listView;
    public SQLiteDatabase db;


    public Fragmento2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento2, container, false);
        fragmentContainer = view.findViewById(R.id.fragmento2);
        db = getActivity().openOrCreateDatabase(DBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
//        criarTabela(db);
//        displayListView(view);
        return view;
    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }

    private void displayListView(View root) {
        cursor = db.rawQuery("Select * from Hotel;",null);//replace to cursor = dbHelper.fetchAllHotels();
        // The desired columns to be bound
        String[] fromFieldNames = {"nombre", "direccion","telefono"};
        // the XML defined views which the data will be bound to
        int[] toViewsID = {R.id.txtNome, R.id.txtEndereco, R.id.txtTelefone};
        scAdapter=new SimpleCursorAdapter(getActivity(), R.layout.activity_contato, cursor, fromFieldNames, toViewsID, 0);
        listView = (ListView) root.findViewById(R.id.h_listagem);
        listView.setAdapter(scAdapter);// Assign adapter to ListView.... here... the bitch error
    }

    public void criarTabela(SQLiteDatabase database){
        try {

            database.execSQL("CREATE TABLE if not exists Hotel (_id integer PRIMARY KEY AUTOINCREMENT UNIQUE, nombre text, direccion text, telefono text, email text);");
            Cursor ap = database.rawQuery("select * from Hotel;",null);
            if(ap.getCount()<=0){
                database.execSQL("insert into Hotel (nombre,direccion,telefono,email) values ('HOTEL L´ORBE','Poniente 5 #33 entre Madero y Sur 2','01 (272) 72 5 50 33','orbereservaciones@gmail.com');");
                database.execSQL("insert into Hotel (nombre,direccion,telefono,email) values ('HOTEL HA','Av. Oriente 6 No. 263 Col. Centro Orizaba, Veracruz','01 272 72 53699','HotelHa.Orizaba@gmail.com');");
                database.execSQL("insert into Hotel (nombre,direccion,telefono,email) values('GRAND HOTEL DE FRANCE','no hay aun','01 272 72 52311','Sin correo');");
                database.execSQL("insert into Hotel (nombre,direccion,telefono,email) values('HOTEL TRUEBA','Ote. 6 Nº 485, Esq. Sur 11. Orizaba, Ver','01 (272) 724 27 44','reservaciones@hoteltrueba.com');");
                database.execSQL("insert into Hotel (nombre,direccion,telefono,email) values('HOTEL PLUVIOSILLA','Sin datos','No disponible','no disponible');");
                database.execSQL("insert into Hotel (nombre,direccion,telefono,email) values('HOTEL ALAMEDA SUITES','PONIENTE 3 No. 512 COLONIA CENTRO  ORIZABA, VER','01 (272) 72 5 7143','no disponible');");
            }else {
                ap.moveToFirst();
                ap.moveToNext();
//                alertas(ap.getString(1));
            }
            ap.moveToPosition(3);
//            alertas(ap.getString(1));
            db = database;

        }catch (SQLiteException e){
            alertas("Erro desconhecido: "+e.getMessage());
        }
    }

    public void alertas(String alerta){

        Context context = getActivity().getApplicationContext();
        Toast toast = Toast.makeText(context,alerta,Toast.LENGTH_SHORT);
        toast.show();
    }
}
