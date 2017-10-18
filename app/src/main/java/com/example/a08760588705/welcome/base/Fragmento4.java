package com.example.a08760588705.welcome.base;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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


public class Fragmento4 extends Fragment {
    private FrameLayout fragmentContainer;
    public SimpleCursorAdapter scAdapter;
    public Cursor cursor4;
    public ListView listView;
    public SQLiteDatabase db;
    private static final String[] PROJECTION =
            {   ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            };

    private static final String SELECTION = ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + "= 1  " +

            "AND "+ ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE '%cli%' "  ;
    private static final String ORDER_BY = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " DESC" ;
    private static final String[] FROM_COLUMNS = { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };
    private static final int[] TO_IDS = {R.id.txtNome, R.id.txtTelefone};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento4, container, false);
        fragmentContainer = view.findViewById(R.id.fragmento4);
        displayListView(view);

        return view;
    }

    private void displayListView(View root) {
//        Cursor dataCursor = getActivity().getContentResolver().query(
//                ContactsContract.Data.CONTENT_URI,
//                new String[]{ContactsContract.Data.CONTACT_ID, ContactsContract.Data.DATA1},
//                ContactsContract.Data.MIMETYPE + "=?",
//                new String[]{ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE}, null);
//        Cursor gCursor = getActivity().getContentResolver().query(
//                ContactsContract.Groups.CONTENT_URI,
//                new String[]{ContactsContract.Groups._ID, ContactsContract.Groups.TITLE},
//                null,
//                null, null);
//
//        GlobalConfig.groupList.clear();


        cursor4 = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION, SELECTION, null, ORDER_BY);

        scAdapter=new SimpleCursorAdapter(getActivity(), R.layout.activity_contato, cursor4, FROM_COLUMNS, TO_IDS, 1);
        listView = (ListView) root.findViewById(R.id.list_contatos);
        listView.setAdapter(scAdapter);
        TextView txtFrag = (TextView)root.findViewById(R.id.txtFrag4);
        txtFrag.setText(String.valueOf(scAdapter.getCount()));
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
