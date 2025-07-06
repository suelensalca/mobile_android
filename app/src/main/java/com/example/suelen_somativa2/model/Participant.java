package com.example.suelen_somativa2.model;

public class Participant {
    private String nome;
    private String telefone;

    public Participant(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

}
