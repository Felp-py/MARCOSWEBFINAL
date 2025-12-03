package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_entrega")
public class TipoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_entrega")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
