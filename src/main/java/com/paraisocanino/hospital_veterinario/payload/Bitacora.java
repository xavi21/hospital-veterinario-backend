package com.paraisocanino.hospital_veterinario.payload;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@MappedSuperclass
public abstract  class Bitacora {

    @NotBlank
    private LocalDate fechacreacion;

    @NotBlank
    @Size(max = 100)
    private String usuariocreacion;

    @NotBlank
    private LocalDate fechamodificacion;

    @NotBlank
    @Size(max = 100)
    private String usuariomodificacion;

    public LocalDate getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(LocalDate fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public LocalDate getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(LocalDate fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public String getUsuariomodificacion() {
        return usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }
}
