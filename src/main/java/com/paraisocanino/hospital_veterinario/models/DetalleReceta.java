package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import com.paraisocanino.hospital_veterinario.payload.DetalleRecetaCompuesta;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "detalle_receta")
public class DetalleReceta extends Bitacora {

    @EmbeddedId
    private DetalleRecetaCompuesta id;

    private Integer cantidad;

    @NotBlank
    @Size(max = 300)
    private String indicaciones;

    public DetalleRecetaCompuesta getId() {
        return id;
    }

    public void setId(DetalleRecetaCompuesta id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public @NotBlank @Size(max = 300) String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(@NotBlank @Size(max = 300) String indicaciones) {
        this.indicaciones = indicaciones;
    }
}
