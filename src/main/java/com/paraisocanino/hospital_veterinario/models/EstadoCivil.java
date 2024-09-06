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
@Table(name = "estado_civil")
public class EstadoCivil extends Bitacora {

    @Id
    @GenericGenerator(name = "estado_civil", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "estado_civil", value = "sequence_estado_civil") })
    @GeneratedValue(generator = "secuence_estado_civil", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_estado_civil", allocationSize=1)
    private Integer id_estado_civil;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getId_estado_civil() {
        return id_estado_civil;
    }

    public void setId_estado_civil(Integer id_estado_civil) {
        this.id_estado_civil = id_estado_civil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
