package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "medicamento")
public class Medicamento extends Bitacora {

    @Id
    @GenericGenerator(name = "medicamento", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "medicamento", value = "sequence_medicamento")})
    @GeneratedValue(generator = "secuence_medicamento", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_medicamento", allocationSize = 1)
    private Integer idmedicamento;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 300)
    private String descripcion;

    private Integer idcasamedica;

    private Integer idcomponenteprincipal;

    public Integer getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Integer idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public @NotBlank @Size(max = 100) String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank @Size(max = 100) String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank @Size(max = 300) String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank @Size(max = 300) String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdcasamedica() {
        return idcasamedica;
    }

    public void setIdcasamedica(Integer idcasamedica) {
        this.idcasamedica = idcasamedica;
    }

    public Integer getIdcomponenteprincipal() {
        return idcomponenteprincipal;
    }

    public void setIdcomponenteprincipal(Integer idcomponenteprincipal) {
        this.idcomponenteprincipal = idcomponenteprincipal;
    }
}
