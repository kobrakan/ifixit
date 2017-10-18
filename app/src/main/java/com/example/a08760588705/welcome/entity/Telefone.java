package com.example.a08760588705.welcome.entity;

import android.provider.BaseColumns;

/**
 * Created by 08760588705 on 11/09/17.
 */

public class Telefone implements BaseColumns {
    private String Telefone;
    public String getTelefone() {
        return Telefone;

    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Telefone: " + Telefone;
    }
}
