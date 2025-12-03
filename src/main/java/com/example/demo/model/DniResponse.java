package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DniResponse {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("dni")
    private String dni;
    
    @JsonProperty("nombres")
    private String nombres;
    
    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;
    
    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;
    
    @JsonProperty("codVerifica")
    private Integer codVerifica;
    
    @JsonProperty("codVerificaLetra")
    private String codVerificaLetra;

    // Getters y Setters para todos los campos
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getCodVerifica() {
        return codVerifica;
    }

    public void setCodVerifica(Integer codVerifica) {
        this.codVerifica = codVerifica;
    }

    public String getCodVerificaLetra() {
        return codVerificaLetra;
    }

    public void setCodVerificaLetra(String codVerificaLetra) {
        this.codVerificaLetra = codVerificaLetra;
    }
}