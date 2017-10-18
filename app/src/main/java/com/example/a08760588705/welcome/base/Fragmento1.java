package com.example.a08760588705.welcome.base;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.example.a08760588705.welcome.R;
import com.example.a08760588705.welcome.entity.Atendimento;
import com.example.a08760588705.welcome.provider.DBHelper;
import com.example.a08760588705.welcome.utils.IfixitUtils;
import com.example.a08760588705.welcome.utils.Mask;


public class Fragmento1 extends Fragment{
    private FrameLayout fragmentContainer;
    public SQLiteDatabase db;
    Spinner spinContatos ;
    Button btnNovoCliente ;
    Button btnSalvar ;
    Button btnLimpar ;
    EditText txtDefeito;
    EditText txtSolucao;
    EditText txtDataAtendimento;

    //    private static final String[] PROJECTION = {ContactsContract.Contacts._ID, ContactsContract.Contacts.LOOKUP_KEY, ContactsContract.Contacts.DISPLAY_NAME};
    private static final String[] PROJECTION =
            {       ContactsContract.CommonDataKinds.Phone._ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
//                    ContactsContract.CommonDataKinds.Phone.NUMBER
            };
    private static final String SELECTION = ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + " = 1 AND" +
            " "+ ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE '%CLI%' "  ;
    private static final String ORDER_BY = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC" ;
    private static final String[] FROM_COLUMNS = { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
//            ContactsContract.CommonDataKinds.Phone.NUMBER
    };
    private SimpleCursorAdapter mCursorAdapter;
    private Cursor mCursor;
    private static final int[] TO_IDS = {R.id.txtNome, R.id.txtTelefone, R.id.txtEndereco};


    public Fragmento1() {
    }


    @Override
    public void onResume() {
        super.onResume();
        carregarSpinner(getView().getRootView());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento1, container, false);
        db = getActivity().openOrCreateDatabase(DBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
        spinContatos = view.findViewById(R.id.spin_contatos);
        btnNovoCliente = view.findViewById(R.id.btnNovoCliente);
        btnSalvar = view.findViewById(R.id.btnSalvar);
        btnLimpar = view.findViewById(R.id.btnLimpar);
        txtDataAtendimento = view.findViewById(R.id.txtData);
        txtDefeito = view.findViewById(R.id.txtDefeito);
        txtSolucao = view.findViewById(R.id.txtSolucao);


        carregarSpinner(view);

        txtDataAtendimento.addTextChangedListener(Mask.insert("##/##/####",txtDataAtendimento));

        btnNovoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
                i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                startActivity(i);
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(Atendimento.CLIENTE, String.valueOf(spinContatos.getSelectedItemPosition()));
                values.put(Atendimento.DATA_ATENDIMENTO, txtDataAtendimento.getText().toString());
                values.put(Atendimento.DEFEITO, txtDefeito.getText().toString());
                values.put(Atendimento.SOLUCAO, txtSolucao.getText().toString());
                salvarDados(values);
            }
        });


        spinContatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    public void criarTabela(SQLiteDatabase database){
        try {

            database.execSQL("CREATE TABLE if not exists "+ DBHelper.ATENDIMENTO_TABLE +" " +
                    "(_id integer PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    Atendimento.CLIENTE +" text, " +
                    Atendimento.TIPO_ATENDIMENTO +" integer, " +
                    Atendimento.DATA_ATENDIMENTO +" date, " +
                    Atendimento.DEFEITO +" text, "+
                    Atendimento.SOLUCAO +" text );");

            db = database;

        }catch (SQLiteException e){
            IfixitUtils.alertas(getActivity(), "Erro desconhecido: "+e.getMessage());
        }
    }
    private void salvarDados(ContentValues values){
        try{
              getActivity().getContentResolver().insert(Atendimento.CONTENT_URI,values);
            IfixitUtils.alertas(getActivity(),"Dados Salvos com sucesso!");

        }catch (SQLiteException e){
            IfixitUtils.alertas(getActivity(), "Erro de banco: "+e.getMessage());
        }
        catch (Exception e){
            IfixitUtils.alertas(getActivity(), "Erro geral: "+e.getMessage());
        }
    }

    private void carregarSpinner(View root){
        Cursor c = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION, SELECTION, null, ORDER_BY);
        mCursorAdapter = new SimpleCursorAdapter(root.getContext(), android.R.layout.simple_spinner_item, c, FROM_COLUMNS, new int[]{android.R.id.text1}, 0 );
        mCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinContatos.setAdapter(mCursorAdapter);
    }
}
