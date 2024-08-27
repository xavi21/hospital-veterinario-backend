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
@Table(name = "ambulancia")
public class Ambulancia extends Bitacora {
    @Id
    @GenericGenerator(name = "ambulancia", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "ambulancia", value = "sequence_ambulancia") })
    @GeneratedValue(generator = "secuence_ambulancia", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_ambulancia", allocationSize=1)
    private Integer idAmbulancia;

    @NotBlank
    @Size(max = 100)
    private String placa;
    @NotBlank
    @Size(max = 100)
    private String marca;
    @NotBlank
    @Size(max = 100)
    private String modelo;

    @Size(max = 100)
    private String latitud;
    
    @Size(max = 100)
    private String longitud;

    private Integer idEmpleado;

    public Integer getIdAmbulancia() {
        return idAmbulancia;
    }

    public void setIdAmbulancia(Integer idAmbulancia) {
        this.idAmbulancia = idAmbulancia;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
