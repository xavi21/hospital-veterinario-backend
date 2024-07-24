package com.paraisocanino.hospital_veterinario.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "opcion")
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idopcion;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private Integer ordenmenu;

    @NotBlank
    @Size(max = 100)
    private String pagina;

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

    public Integer getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Integer idopcion) {
        this.idopcion = idopcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdenmenu() {
        return ordenmenu;
    }

    public void setOrdenmenu(Integer ordenmenu) {
        this.ordenmenu = ordenmenu;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

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
