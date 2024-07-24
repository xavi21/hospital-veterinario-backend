package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario extends Bitacora {

    @Id
    @NotBlank
    @Size(max = 100)
    private String idusuario;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    private LocalDate ultimafechaingreso;

    @NotBlank
    private Integer intentosdeacceso;

    @Size(max = 100)
    private String sesionactual;

    @NotBlank
    private LocalDate ultimafechacambiopassword;

    @Size(max = 100)
    private String telefonomovil;

    @NotBlank
    private Integer idstatususuario;

    @ManyToOne
    @JoinColumn(name = "idsucursal", nullable = false)
    private Sucursal idsucursal;


    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getUltimafechaingreso() {
        return ultimafechaingreso;
    }

    public void setUltimafechaingreso(LocalDate ultimafechaingreso) {
        this.ultimafechaingreso = ultimafechaingreso;
    }

    public Integer getIntentosdeacceso() {
        return intentosdeacceso;
    }

    public void setIntentosdeacceso(Integer intentosdeacceso) {
        this.intentosdeacceso = intentosdeacceso;
    }

    public String getSesionactual() {
        return sesionactual;
    }

    public void setSesionactual(String sesionactual) {
        this.sesionactual = sesionactual;
    }

    public LocalDate getUltimafechacambiopassword() {
        return ultimafechacambiopassword;
    }

    public void setUltimafechacambiopassword(LocalDate ultimafechacambiopassword) {
        this.ultimafechacambiopassword = ultimafechacambiopassword;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public Integer getIdstatususuario() {
        return idstatususuario;
    }

    public void setIdstatususuario(Integer idstatususuario) {
        this.idstatususuario = idstatususuario;
    }

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }
}
