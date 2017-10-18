package com.example.a08760588705.welcome.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a08760588705.welcome.entity.Agendamento;
import com.example.a08760588705.welcome.entity.Atendimento;

/**
 * Created by 08760588705 on 06/09/17.
 */

public class DBHelper extends SQLiteOpenHelper {


    // Authority do nosso provider, a ser usado nas Uris.
    public static final String AUTHORITY =
            "com.example.a08760588705.welcome.provider";

    // Nome do arquivo que irá conter o banco de dados.
    public static  final String DATABASE_NAME = "ifixit.db";

    // Versao do banco de dados.
    // Este valor é importante pois é usado em futuros updates do DB.
    public static  final int  DATABASE_VERSION = 1;

    // Nome das tabelas que irá conter os dados dos clientes.
    public static final String CONTATOS_TABLE = "contatos";
    public static final String COMPUTADOR_TABLE = "computadores";
    public static final String ATENDIMENTO_TABLE = "atendimentos";
    public static final String PAGAMENTO_TABLE = "pagamentos";
    public static final String AGENDAMENTO_TABLE = "agendamentos";

    // 'Id' da Uri referente aos atendimentos do usuário.
    public static final int ATENDIMENTO = 1;
    // 'Id' da Uri referente aos atendimentos do usuário.
    public static final int CONTATOS = 2;
    // 'Id' da Uri referente aos atendimentos do usuário.
    public static final int ENDERECO = 3;
    // 'Id' da Uri referente aos atendimentos do usuário.
    public static final int PAGAMENTO = 4;
    // 'Id' da Uri referente aos agendamentos do usuário.
    public static final int AGENDAMENTO = 5;


    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE if not exists "+ DBHelper.ATENDIMENTO_TABLE +" " +
                "(" +
                Atendimento.ID +" integer PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                Atendimento.CLIENTE +" text, " +
                Atendimento.TIPO_ATENDIMENTO +" integer, " +
                Atendimento.DATA_ATENDIMENTO +" date, " +
                Atendimento.DEFEITO +" text, "+
                Atendimento.SOLUCAO +" text );");

        db.execSQL("CREATE TABLE if not exists "+ DBHelper.AGENDAMENTO_TABLE +" " +
                "(" +
                Agendamento.ID +" integer PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                Agendamento.CLIENTE +" text, " +
                Agendamento.DATA_INICIO +" date, " +
                Agendamento.DATA_FIM +" date, " +
                Agendamento.TITULO +" text );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
