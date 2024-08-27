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
@Table(name = "tipo_mascota")
public class TipoMascota extends Bitacora {

    @Id
    @GenericGenerator(name = "tipo_mascota", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "tipo_mascota", value = "sequence_tipo_mascota") })
    @GeneratedValue(generator = "secuence_tipo_mascota", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_tipo_mascota", allocationSize=1)
    private Integer idTipoMascota;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
