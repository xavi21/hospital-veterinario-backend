package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "hospitalizacion_laboratorio")
public class HospitalizacionLaboratorio extends Bitacora {

    @Id
    @GenericGenerator(name = "hospitalizacion_laboratorio", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "hospitalizacion_laboratorio", value = "sequence_hospitalizacion_laboratorio")})
    @GeneratedValue(generator = "secuence_hospitalizacion_laboratorio", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_hospitalizacion_laboratorio", allocationSize = 1)
    private Integer idhospitalizacionlaboratorio;

    private Integer idhospitalizacion;
    private Integer idlaboratorio;
    private LocalDate fechasolicitud;
    private LocalDate fecharesultado;
    @Column(columnDefinition = "TEXT")
    private String resultado;

    public Integer getIdhospitalizacionlaboratorio() {
        return idhospitalizacionlaboratorio;
    }

    public void setIdhospitalizacionlaboratorio(Integer idhospitalizacionlaboratorio) {
        this.idhospitalizacionlaboratorio = idhospitalizacionlaboratorio;
    }

    public Integer getIdhospitalizacion() {
        return idhospitalizacion;
    }

    public void setIdhospitalizacion(Integer idhospitalizacion) {
        this.idhospitalizacion = idhospitalizacion;
    }

    public Integer getIdlaboratorio() {
        return idlaboratorio;
    }

    public void setIdlaboratorio(Integer idlaboratorio) {
        this.idlaboratorio = idlaboratorio;
    }

    public LocalDate getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(LocalDate fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public LocalDate getFecharesultado() {
        return fecharesultado;
    }

    public void setFecharesultado(LocalDate fecharesultado) {
        this.fecharesultado = fecharesultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
