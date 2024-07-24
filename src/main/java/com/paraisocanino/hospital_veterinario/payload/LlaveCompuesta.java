package com.paraisocanino.hospital_veterinario.payload;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LlaveCompuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idPart1;
    private Integer idPart2;
    private Integer idPart3;

    public LlaveCompuesta() {}

    public LlaveCompuesta(String idPart1, Integer idPart2, Integer idPart3) {
        this.idPart1 = idPart1;
        this.idPart2 = idPart2;
        this.idPart3 = idPart3;
    }

    public String getIdPart1() {
        return idPart1;
    }

    public void setIdPart1(String idPart1) {
        this.idPart1 = idPart1;
    }

    public Integer getIdPart2() {
        return idPart2;
    }

    public void setIdPart2(Integer idPart2) {
        this.idPart2 = idPart2;
    }

    public Integer getIdPart3() {
        return idPart3;
    }

    public void setIdPart3(Integer idPart3) {
        this.idPart3 = idPart3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LlaveCompuesta that = (LlaveCompuesta) o;
        return Objects.equals(idPart1, that.idPart1) &&
                Objects.equals(idPart2, that.idPart2) &&
                Objects.equals(idPart3, that.idPart3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPart1, idPart2, idPart3);
    }
}
