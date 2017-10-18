package com.example.a08760588705.welcome.entity;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by 08760588705 on 12/09/17.
 */

public class Pagamento implements BaseColumns {

    private Contato contato;
    private Atendimento atendimento;
    private Float valor;
    private String modalidade;
    private Date dataPagamento;

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
