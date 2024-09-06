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
@Table(name = "genero")
public class Genero extends Bitacora {
    @Id
    @GenericGenerator(name = "genero", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "genero", value = "sequence_genero") })
    @GeneratedValue(generator = "secuence_genero", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_genero", allocationSize=1)
    private Integer id_genero;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getId_genero() {
        return id_genero;
    }

    public void setId_genero(Integer id_genero) {
        this.id_genero = id_genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
