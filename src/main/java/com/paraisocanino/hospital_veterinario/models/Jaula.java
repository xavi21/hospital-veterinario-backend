package com.paraisocanino.hospital_veterinario.models;

import org.hibernate.annotations.GenericGenerator;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "jaula")
public class Jaula extends Bitacora {

    @Id
    @GenericGenerator(name = "jaula", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "jaula", value = "sequence_jaula") })
    @GeneratedValue(generator = "secuence_jaula", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_jaula", allocationSize=1)
    private Integer idJaula;
    
    @NotBlank
    @Size(max = 100)
    private String descripcion;

    public Integer getIdJaula() {
        return idJaula;
    }

    public void setIdJaula(Integer idJaula) {
        this.idJaula = idJaula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
