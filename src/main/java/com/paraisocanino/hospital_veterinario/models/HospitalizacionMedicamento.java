package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "hospitalizacion_medicamento")
public class HospitalizacionMedicamento extends Bitacora {

    @Id
    @GenericGenerator(name = "hospitalizacion_medicamento", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "hospitalizacion_medicamento", value = "sequence_hospitalizacion_medicamento")})
    @GeneratedValue(generator = "secuence_hospitalizacion_medicamento", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_hospitalizacion_medicamento", allocationSize = 1)
    private Integer idhospitalizacionmedicamento;

    private Integer idhospitalizacion;
    private Integer idmedicamento;
    @Column(columnDefinition = "TEXT")
    private String observaciones;

    public Integer getIdhospitalizacionmedicamento() {
        return idhospitalizacionmedicamento;
    }

    public void setIdhospitalizacionmedicamento(Integer idhospitalizacionmedicamento) {
        this.idhospitalizacionmedicamento = idhospitalizacionmedicamento;
    }

    public Integer getIdhospitalizacion() {
        return idhospitalizacion;
    }

    public void setIdhospitalizacion(Integer idhospitalizacion) {
        this.idhospitalizacion = idhospitalizacion;
    }

    public Integer getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Integer idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
