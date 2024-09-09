package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "empleado")
public class Empleado extends Bitacora {

    @Id
    @GenericGenerator(name = "persona", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "persona", value = "sequence_persona")})
    @GeneratedValue(generator = "secuence_persona", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_persona", allocationSize = 1)
    private Integer idempleado;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String apellido;

    private LocalDate fechanacimiento;

    private LocalDate fechacontratacion;

    private int idsucursal;

    private int idpuesto;

    private int idestadocivil;

    private int idgenero;

    private int idstatusempleado;

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public @NotBlank @Size(max = 100) String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank @Size(max = 100) String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank @Size(max = 100) String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank @Size(max = 100) String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public LocalDate getFechacontratacion() {
        return fechacontratacion;
    }

    public void setFechacontratacion(LocalDate fechacontratacion) {
        this.fechacontratacion = fechacontratacion;
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public int getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(int idpuesto) {
        this.idpuesto = idpuesto;
    }

    public int getIdestadocivil() {
        return idestadocivil;
    }

    public void setIdestadocivil(int idestadocivil) {
        this.idestadocivil = idestadocivil;
    }

    public int getIdgenero() {
        return idgenero;
    }

    public void setIdgenero(int idgenero) {
        this.idgenero = idgenero;
    }

    public int getIdstatusempleado() {
        return idstatusempleado;
    }

    public void setIdstatusempleado(int idstatusempleado) {
        this.idstatusempleado = idstatusempleado;
    }
}
