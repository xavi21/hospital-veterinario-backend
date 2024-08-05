package com.paraisocanino.hospital_veterinario.payload;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LlaveCompuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idusuario;
    private Integer idmenu;
    private Integer idopcion;

    public LlaveCompuesta() {}

    public LlaveCompuesta(String idPart1, Integer idPart2, Integer idPart3) {
        this.idusuario = idPart1;
        this.idmenu = idPart2;
        this.idopcion = idPart3;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LlaveCompuesta that = (LlaveCompuesta) o;
        return Objects.equals(idusuario, that.idusuario) &&
                Objects.equals(idmenu, that.idmenu) &&
                Objects.equals(idopcion, that.idopcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idusuario, idmenu, idopcion);
    }
}
