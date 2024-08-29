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
@Table(name = "casa_medica")
public class CasaMedica extends Bitacora {
    @Id
    @GenericGenerator(name = "casa_medica", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "casa_medica", value = "sequence_casa_medica") })
    @GeneratedValue(generator = "secuence_casa_medica", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_casa_medica", allocationSize=1)
    private Integer idcasamedica;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String nombrecomercial;

    public Integer getIdcasamedica() {
        return idcasamedica;
    }

    public void setIdcasamedica(Integer idcasamedica) {
        this.idcasamedica = idcasamedica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }
}
