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
@Table(name = "tipo_medida_peso")
public class TipoMedidaPeso extends Bitacora {
    @Id
    @GenericGenerator(name = "tipo_medida_peso", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "tipo_medida_peso", value = "sequence_tipo_medida_peso") })
    @GeneratedValue(generator = "secuence_tipo_medida_peso", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_tipo_medida_peso", allocationSize=1)
    private Integer idTipoMedidaPeso;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getIdTipoMedidaPeso() {
        return idTipoMedidaPeso;
    }

    public void setIdTipoMedidaPeso(Integer idTipoMedidaPeso) {
        this.idTipoMedidaPeso = idTipoMedidaPeso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
