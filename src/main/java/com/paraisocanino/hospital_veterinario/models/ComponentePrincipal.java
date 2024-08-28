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
@Table(name = "componente_principal")
public class ComponentePrincipal extends Bitacora {

    @Id
    @GenericGenerator(name = "componente_principal", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "componente_principal", value = "sequence_componente_principal") })
    @GeneratedValue(generator = "secuence_componente_principal", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_componente_principal", allocationSize=1)
    private Integer idComponentePrincipal;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getIdComponentePrincipal() {
        return idComponentePrincipal;
    }

    public void setIdComponentePrincipal(Integer idComponentePrincipal) {
        this.idComponentePrincipal = idComponentePrincipal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
