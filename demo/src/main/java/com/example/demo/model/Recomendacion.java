package com.example.demo.model;

public class Recomendacion {
    private String titulo;
    private String autor;
    private String genero;
    private String motivo;

    public Recomendacion() {}

    public Recomendacion(String titulo, String autor, String genero, String motivo) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.motivo = motivo;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
