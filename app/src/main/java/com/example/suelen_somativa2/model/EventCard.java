package com.example.suelen_somativa2.model;

public class EventCard {
    private String titulo;
    private String dataHora;
    private int vagas;
    private int imagemResId;

    public EventCard(String titulo, String dataHora, int vagas, int imagemResId) {
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.vagas = vagas;
        this.imagemResId = imagemResId;
    }

    public String getTitulo() { return titulo; }
    public String getDataHora() { return dataHora; }
    public int getVagas() { return vagas; }
    public int getImagemResId() { return imagemResId; }
}
