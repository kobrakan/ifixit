package com.example.a08760588705.welcome.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a08760588705.welcome.entity.Agendamento;
import com.example.a08760588705.welcome.entity.Atendimento;

import java.util.HashMap;

import static com.example.a08760588705.welcome.provider.DBHelper.AGENDAMENTO;
import static com.example.a08760588705.welcome.provider.DBHelper.AGENDAMENTO_TABLE;
import static com.example.a08760588705.welcome.provider.DBHelper.ATENDIMENTO;
import static com.example.a08760588705.welcome.provider.DBHelper.ATENDIMENTO_TABLE;

public class AtendimentoProvider extends ContentProvider {

    public static final String AUTHORITY =
            "com.example.a08760588705.welcome.provider";

    public static final String TAG = "AtendimentoProvider";

    private DBHelper mHelper;

    private static final UriMatcher mMatcher;

    private static HashMap<String, String> mProjection;

    static {
        mProjection = new HashMap<String, String>();
        mProjection.put(Atendimento.ID, Atendimento.ID);
        mProjection.put(Atendimento.CLIENTE, Atendimento.CLIENTE);
        mProjection.put(Atendimento.DEFEITO, Atendimento.DEFEITO);
        mProjection.put(Atendimento.SOLUCAO, Atendimento.SOLUCAO);
        mProjection.put(Atendimento.DATA_ATENDIMENTO, Atendimento.DATA_ATENDIMENTO);
    }

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(DBHelper.AUTHORITY, ATENDIMENTO_TABLE, ATENDIMENTO);
        mMatcher.addURI(DBHelper.AUTHORITY, AGENDAMENTO_TABLE, AGENDAMENTO);
    }
    public AtendimentoProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        switch (mMatcher.match(uri)) {
            case ATENDIMENTO:
                count = db.delete(ATENDIMENTO_TABLE, selection, selectionArgs);
                break;
            case AGENDAMENTO:
                count = db.delete(AGENDAMENTO_TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI Desconhecida " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        switch (mMatcher.match(uri)) {
            case ATENDIMENTO:
                return Atendimento.CONTENT_TYPE;
            case AGENDAMENTO:
                return Agendamento.CONTENT_TYPE;
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
        db = mHelper.getWritableDatabase();

        switch (mMatcher.match(uri)) {
            case ATENDIMENTO:
                rowId = db.insert(ATENDIMENTO_TABLE, null, values);
                if(rowId >0){
                    Uri atendimentoUri = ContentUris.withAppendedId(Atendimento.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(atendimentoUri, null);
                    retornoUri = atendimentoUri;
                }
                break;
            case AGENDAMENTO:
                rowId = db.insert(AGENDAMENTO_TABLE, null, values);
                if(rowId >0){
                    Uri agendamentoUri = ContentUris.withAppendedId(Agendamento.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(agendamentoUri, null);
                    retornoUri = agendamentoUri;
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }
        return retornoUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
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
            case ATENDIMENTO:
                builder.setTables(ATENDIMENTO_TABLE);
                builder.setProjectionMap(mProjection);
                break;
            case AGENDAMENTO:
                builder.setTables(AGENDAMENTO_TABLE);
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
        // TODO: Implement this to handle requests to update one or more rows.
        int count;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        switch (mMatcher.match(uri)) {
            case ATENDIMENTO:
                count = db.update(ATENDIMENTO_TABLE, values, selection, selectionArgs);
                break;
            case AGENDAMENTO:
                count = db.update(ATENDIMENTO_TABLE, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);

        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
