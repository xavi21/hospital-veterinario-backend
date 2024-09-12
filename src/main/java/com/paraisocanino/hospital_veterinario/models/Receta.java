package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "receta")
public class Receta extends Bitacora {

    @Id
    @GenericGenerator(name = "receta", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "receta", value = "sequence_receta")})
    @GeneratedValue(generator = "secuence_receta", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_receta", allocationSize = 1)
    private Integer idreceta;

    private Integer idconsulta;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    public Integer getIdreceta() {
        return idreceta;
    }

    public void setIdreceta(Integer idreceta) {
        this.idreceta = idreceta;
    }

    public Integer getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
