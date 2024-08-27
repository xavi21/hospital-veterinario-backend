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
@Table(name = "talla")
public class Talla extends Bitacora {

    @Id
    @GenericGenerator(name = "talla", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "talla", value = "sequence_talla") })
    @GeneratedValue(generator = "secuence_talla", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_talla", allocationSize=1)
    private Integer idTalla;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
