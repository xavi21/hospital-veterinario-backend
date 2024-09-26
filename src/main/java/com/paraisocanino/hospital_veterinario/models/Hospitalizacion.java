package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "hospitalizacion")
public class Hospitalizacion  extends Bitacora {

    @Id
    @GenericGenerator(name = "hospitalizacion", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "hospitalizacion", value = "sequence_hospitalizacion")})
    @GeneratedValue(generator = "secuence_hospitalizacion", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_hospitalizacion", allocationSize = 1)
    private Integer idhospitalizacion;

    private Integer idmascota;
    private Integer idjaula;
    private LocalDate fechaingreso;
    private LocalDate fechasalida;
    @Column(columnDefinition = "TEXT")
    private String motivo;
    @Column(columnDefinition = "TEXT")
    private String observaciones;

    public Integer getIdhospitalizacion() {
        return idhospitalizacion;
    }

    public void setIdhospitalizacion(Integer idhospitalizacion) {
        this.idhospitalizacion = idhospitalizacion;
    }

    public Integer getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(Integer idmascota) {
        this.idmascota = idmascota;
    }

    public Integer getIdjaula() {
        return idjaula;
    }

    public void setIdjaula(Integer idjaula) {
        this.idjaula = idjaula;
    }

    public LocalDate getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(LocalDate fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public LocalDate getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(LocalDate fechasalida) {
        this.fechasalida = fechasalida;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
