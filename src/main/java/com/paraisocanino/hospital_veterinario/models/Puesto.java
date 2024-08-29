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
@Table(name = "puesto")
public class Puesto extends Bitacora {
    @Id
    @GenericGenerator(name = "puesto", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "puesto", value = "sequence_puesto") })
    @GeneratedValue(generator = "secuence_puesto", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_puesto", allocationSize=1)
    private Integer idpuesto;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(Integer idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }
}
