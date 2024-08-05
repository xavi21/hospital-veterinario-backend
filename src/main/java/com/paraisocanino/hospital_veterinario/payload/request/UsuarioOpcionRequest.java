package com.paraisocanino.hospital_veterinario.payload.request;

public class UsuarioOpcionRequest {
    private String idUsuario;
    private Integer idMenu;
    private Integer idOpcion;
    private Integer alta;
    private Integer baja;
    private Integer cambio;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Integer getAlta() {
        return alta;
    }

    public void setAlta(Integer alta) {
        this.alta = alta;
    }

    public Integer getBaja() {
        return baja;
    }

    public void setBaja(Integer baja) {
        this.baja = baja;
    }

    public Integer getCambio() {
        return cambio;
    }

    public void setCambio(Integer cambio) {
        this.cambio = cambio;
    }
}
