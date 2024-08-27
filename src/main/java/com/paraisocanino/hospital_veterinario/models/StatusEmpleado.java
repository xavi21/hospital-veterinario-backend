
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
@Table(name = "status_empleado")
public class StatusEmpleado extends Bitacora {
    @Id
    @GenericGenerator(name = "status_empleado", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "status_empleado", value = "sequence_status_empleado") })
    @GeneratedValue(generator = "secuence_status_empleado", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_status_empleado", allocationSize=1)
    private Integer idstatusempleado;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    public Integer getIdStatusEmpleado() {
        return idstatusempleado;
    }

    public void setIdStatusEmpleado(Integer idStatusEmpleado) {
        this.idstatusempleado = idStatusEmpleado;
    }
    
    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }
}