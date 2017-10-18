package com.example.a08760588705.welcome.entity;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.a08760588705.welcome.provider.AtendimentoProvider;
import com.example.a08760588705.welcome.provider.QuickNoteProvider;

import java.util.Date;

/**
 * Created by 08760588705 on 12/09/17.
 */

public class Atendimento implements BaseColumns{


    public static final String ID= "_id";
    public static final String CLIENTE = "cliente";
    public static final String TIPO_ATENDIMENTO = "tipo_atendimento";
    public static final String DEFEITO ="defeito";
    public static final String SOLUCAO = "solucao";
    public static final String DATA_ATENDIMENTO="dataAtendimento";

    public static final Uri CONTENT_URI = Uri.parse("content://"+ AtendimentoProvider.AUTHORITY + "/atendimentos");

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/"+ AtendimentoProvider.AUTHORITY;

    private int id;
    private int cliente;
    private int tipoAtendimento;
    private String defeito;
    private String solucao;
    private Date dataAtendimento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(int tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }


}
