package com.example.a08760588705.welcome.entity;

import android.provider.BaseColumns;

import java.util.List;

/**
 * Created by 08760588705 on 11/09/17.
 */
public class Contato implements BaseColumns {

    private static final String CONTATO_ID = "id";
    private static final String CONTATO_NOME = "nome";
//    private static final String CONTATO_TELEFONE = "telefone";
//    private static final String CONTATO_ENDERECO = "endereco";

    private String id;
    private String nome;
    private String email;
    private List<Telefone> telefones;
    private Endereco endereco;
    private List<com.example.a08760588705.welcome.entity.Atendimento> atendimentos;
    private List<Pagamento> pagamentos;

    public List<com.example.a08760588705.welcome.entity.Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
}
