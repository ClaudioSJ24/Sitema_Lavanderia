package com.sanchez.app.proyecto.models;

public class Ropa {

    private Long idRopa;
    private Integer pantalones;
    private  Integer camisas;
    private  Integer vestidos;
    private  Integer playeras;
    private Integer faldas;
    private Float pesoTotal;
    private Float totalPagar;

    public Long getIdRopa() {
        return idRopa;
    }

    public void setIdRopa(Long idRopa) {
        this.idRopa = idRopa;
    }

    public Integer getPantalones() {
        return pantalones;
    }

    public void setPantalones(Integer pantalones) {
        this.pantalones = pantalones;
    }

    public Integer getCamisas() {
        return camisas;
    }

    public void setCamisas(Integer camisas) {
        this.camisas = camisas;
    }

    public Integer getVestidos() {
        return vestidos;
    }

    public void setVestidos(Integer vestidos) {
        this.vestidos = vestidos;
    }

    public Integer getPlayeras() {
        return playeras;
    }

    public void setPlayeras(Integer playeras) {
        this.playeras = playeras;
    }

    public Integer getFaldas() {
        return faldas;
    }

    public void setFaldas(Integer faldas) {
        this.faldas = faldas;
    }

    public Float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Float getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Float totalPagar) {
        this.totalPagar = totalPagar;
    }
}
