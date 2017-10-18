package com.example.a08760588705.welcome.base;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.TextView;

import com.example.a08760588705.welcome.R;
import com.example.a08760588705.welcome.entity.Atendimento;
import com.example.a08760588705.welcome.provider.DBHelper;
import com.example.a08760588705.welcome.utils.IfixitUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragmento3 extends Fragment {
    private FrameLayout fragmentContainer;
    public SimpleCursorAdapter scAdapter;
    public ListView listView;
    private static final int[] TO_IDS = {R.id.txtNome, R.id.txtTelefone, R.id.txtEndereco};
    Cursor cursor;
    public SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento3, container, false);
        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragmento3);
        db = getActivity().openOrCreateDatabase(DBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
//        loadGroups(view);
        displayListView(view);
        return view;
    }

    public Fragmento3() {
    }

    @Override
    public void onStart() {
        super.onStart();
        displayListView(getView().getRootView());
    }



    @Override
    public void onResume() {
        super.onResume();
        displayListView(getView().getRootView());

    }

    private void displayListView(View view) {
        cursor = getActivity().getContentResolver().query(Atendimento.CONTENT_URI, null, null, null, null);

        String[] fromFieldNames = {"_id",Atendimento.CLIENTE, Atendimento.DATA_ATENDIMENTO, Atendimento.DEFEITO, Atendimento.SOLUCAO};
        int[] toViewsID = {R.id.lblCliente, R.id.lblDataAtendimento, R.id.lblDefeito, R.id.lblSolucao};
         // the XML defined views which the data will be bound to
        IfixitUtils.alertas(getActivity(), String.valueOf(cursor.getCount()));
        scAdapter=new SimpleCursorAdapter(getActivity(), R.layout.list_contato, cursor, fromFieldNames, toViewsID, 0);
        listView = (ListView) view.findViewById(R.id.list_grupos);
        listView.setAdapter(scAdapter);
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

}
