package com.example.a08760588705.welcome.entity;

import android.net.Uri;

import com.example.a08760588705.welcome.provider.AtendimentoProvider;

import java.util.Date;

/**
 * Created by 08760588705 on 17/10/17.
 */

public class Agendamento {

    public static final String ID= "_id";
    public static final String CLIENTE = "cliente";
    public static final String DATA_INICIO= "data_inicio";
    public static final String DATA_FIM ="data_fim";
    public static final String TITULO = "titulo";

    public static final Uri CONTENT_URI = Uri.parse("content://"+ AtendimentoProvider.AUTHORITY + "/agendamentos");

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/"+ AtendimentoProvider.AUTHORITY;






    private Date dataInicio;
    private Date dataFim;
    private String titulo;
    private String cliente;


    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
