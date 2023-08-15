package com.sanchez.app.proyecto.models;

public class Direccion {

    private Long idDireccion;
    private String calle;
    private String numero;
    private String colonia;
    private String ciudad;
    private String estado;
    private String cp;

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long id_direccion) {
        this.idDireccion = id_direccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
