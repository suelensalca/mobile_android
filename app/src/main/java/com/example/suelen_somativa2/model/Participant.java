package com.example.suelen_somativa2.model;

public class Participant {
    private long id;
    private String nome;
    private String telefone;

    public Participant(long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Participant(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

}
