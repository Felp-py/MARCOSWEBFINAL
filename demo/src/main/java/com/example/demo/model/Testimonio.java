package com.example.demo.model;

public class Testimonio {
    private int id;
    private String cliente;
    private String comentario;
    private int calificacion; 

    public Testimonio() {}

    public Testimonio(int id, String cliente, String comentario, int calificacion) {
        this.id = id;
        this.cliente = cliente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public int getCalificacion() { return calificacion; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }
}
