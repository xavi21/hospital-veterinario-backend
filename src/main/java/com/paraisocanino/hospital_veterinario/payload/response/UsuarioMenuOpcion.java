package com.paraisocanino.hospital_veterinario.payload.response;

public class UsuarioMenuOpcion {

    private String idusuario;

    private Integer idmenu;

    private Integer idopcion;

    private String menu_nombre;

    private String opcion_nombre;

    private Integer alta;

    private Integer baja;

    private Integer cambio;

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Integer idopcion) {
        this.idopcion = idopcion;
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

    public String getMenu_nombre() {
        return menu_nombre;
    }

    public void setMenu_nombre(String menu_nombre) {
        this.menu_nombre = menu_nombre;
    }

    public String getOpcion_nombre() {
        return opcion_nombre;
    }

    public void setOpcion_nombre(String opcion_nombre) {
        this.opcion_nombre = opcion_nombre;
    }
}
