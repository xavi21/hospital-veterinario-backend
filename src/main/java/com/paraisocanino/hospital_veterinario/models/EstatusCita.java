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
@Table(name = "estatus_cita")
public class EstatusCita extends Bitacora {
    @Id
    @GenericGenerator(name = "estatus_cita", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "estatus_cita", value = "sequence_estatus_cita") })
    @GeneratedValue(generator = "secuence_estatus_cita", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_estatus_cita", allocationSize=1)
    private Integer idestatuscita;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getIdestatuscita() {
        return idestatuscita;
    }

    public void setIdestatuscita(Integer idestatuscita) {
        this.idestatuscita = idestatuscita;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

}
