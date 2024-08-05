package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import com.paraisocanino.hospital_veterinario.payload.LlaveCompuesta;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_opcion")
public class UsuarioOpcion extends Bitacora {

    @EmbeddedId
    private LlaveCompuesta id;

    private Integer alta;

    private Integer baja;

    private Integer cambio;

    public LlaveCompuesta getId() {
        return id;
    }

    public void setId(LlaveCompuesta id) {
        this.id = id;
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
