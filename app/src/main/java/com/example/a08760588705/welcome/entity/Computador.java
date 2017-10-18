package com.example.a08760588705.welcome.entity;

import android.provider.BaseColumns;

/**
 * Created by 08760588705 on 12/09/17.
 */

public class Computador implements BaseColumns {

    private String tipo;
    private String descricao;
    private Contato contato;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
