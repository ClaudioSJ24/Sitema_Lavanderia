package com.sanchez.app.proyecto.models;

public class Cliente {

    private Long idCliente;
    private Long ropaId;
    private String nombre;
    private String telefono;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getRopaId() {
        return ropaId;
    }

    public void setRopaId(Long idRopa) {
        this.ropaId = idRopa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
