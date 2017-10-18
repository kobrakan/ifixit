package com.example.a08760588705.welcome.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.example.a08760588705.welcome.entity.Atendimento;

import java.util.HashMap;

import static com.example.a08760588705.welcome.provider.DBHelper.ATENDIMENTO;
import static com.example.a08760588705.welcome.provider.DBHelper.ATENDIMENTO_TABLE;
import static com.example.a08760588705.welcome.provider.DBHelper.NOTES;
import static com.example.a08760588705.welcome.provider.DBHelper.NOTES_TABLE;

public class QuickNoteProvider extends ContentProvider {

    // Authority do nosso provider, a ser usado nas Uris.
    public static final String AUTHORITY =
            "com.example.a08760588705.welcome.provider";

    // Tag usada para imprimir os logs.
    public static final String TAG = "QuickNoteProvider";

    // Instância da classe utilitária

    private DBHelper mHelper;

    // Uri matcher - usado para extrair informações das Uris
    private static final UriMatcher mMatcher;

    private static HashMap<String, String> mProjection;

    static {
        mProjection = new HashMap<String, String>();
        mProjection.put(Notes.NOTE_ID, Notes.NOTE_ID);
        mProjection.put(Notes.TEXT, Notes.TEXT);
    }

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTHORITY, NOTES_TABLE, NOTES);
        mMatcher.addURI(AUTHORITY, ATENDIMENTO_TABLE, ATENDIMENTO);
    }



    public QuickNoteProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        switch (mMatcher.match(uri)) {
            case NOTES:
                count = db.delete(NOTES_TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI Desconhecida " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (mMatcher.match(uri)) {
            case NOTES:
                return Notes.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri retornoUri = uri;
        SQLiteDatabase db;
        long rowId;
        switch (mMatcher.match(uri)) {
            case NOTES:
                 db = mHelper.getWritableDatabase();
                 rowId = db.insert(NOTES_TABLE, null, values);
                if (rowId > 0) {
                    Uri noteUri = ContentUris.withAppendedId(Notes.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(noteUri, null);
                  retornoUri = noteUri;
                }
                break;
            case ATENDIMENTO:
                db = mHelper.getWritableDatabase();
                rowId = db.insert(ATENDIMENTO_TABLE, null, values);
                if(rowId >0){
                    Uri atendimentoUri = ContentUris.withAppendedId(Atendimento.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(atendimentoUri, null);
                    retornoUri = atendimentoUri;
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }
        return retornoUri;
    }



    @Override
    public boolean onCreate() {
        mHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor ;
        switch (mMatcher.match(uri)){
            case NOTES:
                    builder.setTables(NOTES_TABLE);
                    builder.setProjectionMap(mProjection);
                break;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }

        cursor = builder.query(db, projection, selection,selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count;
        switch (mMatcher.match(uri)) {
            case NOTES:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                count = db.update(NOTES_TABLE, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);

        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

}
