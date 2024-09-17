package com.paraisocanino.hospital_veterinario.payload;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleRecetaCompuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idreceta;
    private Integer idmedicamento;

    public DetalleRecetaCompuesta(){}

    public DetalleRecetaCompuesta(Integer idPar1, Integer idPart2) {
        this.idreceta = idPar1;
        this.idmedicamento = idPart2;
    }


    public Integer getIdreceta() {
        return idreceta;
    }

    public void setIdreceta(Integer idreceta) {
        this.idreceta = idreceta;
    }

    public Integer getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Integer idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleRecetaCompuesta that = (DetalleRecetaCompuesta) o;
        return Objects.equals(idreceta, that.idreceta) &&
                Objects.equals(idmedicamento, that.idmedicamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idreceta, idmedicamento);
    }
}
