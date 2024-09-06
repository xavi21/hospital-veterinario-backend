package com.paraisocanino.hospital_veterinario.models;


import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "persona")
public class Persona extends Bitacora {

    @Id
    @GenericGenerator(name = "persona", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "persona", value = "sequence_persona")})
    @GeneratedValue(generator = "secuence_persona", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_persona", allocationSize = 1)
    private Integer idpersona;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String apellido;

    private LocalDate fechanacimiento;

    private Integer id_genero;

    @NotBlank
    @Size(max = 100)
    private String direccion;

    @NotBlank
    @Size(max = 100)
    private String telefono;

    @NotBlank
    @Size(max = 100)
    private String correoelectronico;

    private Integer id_estado_civil;

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public Integer getId_genero() {
        return id_genero;
    }

    public void setId_genero(Integer id_genero) {
        this.id_genero = id_genero;
    }

    public Integer getId_estado_civil() {
        return id_estado_civil;
    }

    public void setId_estado_civil(Integer id_estado_civil) {
        this.id_estado_civil = id_estado_civil;
    }
}
