package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre_cliente", nullable = false, length = 100)
    private String nombreCliente;

    @Column(columnDefinition = "TEXT")
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(name = "correo_cliente", nullable = false, unique = true, length = 100)
    private String correoCliente;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venta> ventas;

    public Cliente() {}

    // GETTERS Y SETTERS

  public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }    

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoCliente() { return correoCliente; }
    public void setCorreoCliente(String correoCliente) { this.correoCliente = correoCliente; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Venta> getVentas() { return ventas; }
    public void setVentas(List<Venta> ventas) { this.ventas = ventas; }
}
