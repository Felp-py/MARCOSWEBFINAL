package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String password;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Cambiar esto: en lugar de Enum, usar Integer
    @Column(name = "id_rol")
    private Integer idRol;

    // Campo transiente (no se persiste en BD) para el Enum Rol
    @Transient
    private Rol rol;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Cliente cliente;

    // Constructor vacío
    public Usuario() {}

    // Constructor
    public Usuario(String nombre, String email, String password, Integer idRol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Método para obtener el Rol como Enum
    public Rol getRol() {
        if (this.rol == null && this.idRol != null) {
            this.rol = convertirIdRolARol(this.idRol);
        }
        return this.rol;
    }

    // Método para asignar Rol y actualizar idRol
    public void setRol(Rol rol) {
        this.rol = rol;
        if (rol != null) {
            this.idRol = (rol == Rol.ADMIN) ? 1 : 2;
        }
    }

    // Convertir id_rol numérico a Enum Rol
    private Rol convertirIdRolARol(Integer idRol) {
        if (idRol == null) return Rol.CLIENTE;
        switch (idRol) {
            case 1: return Rol.ADMIN;
            case 2: return Rol.CLIENTE;
            default: return Rol.CLIENTE;
        }
    }

    // Getters y Setters
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Integer getIdRol() { return idRol; }
    public void setIdRol(Integer idRol) { 
        this.idRol = idRol;
        this.rol = null; // Resetear el enum para que se recalcule
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}