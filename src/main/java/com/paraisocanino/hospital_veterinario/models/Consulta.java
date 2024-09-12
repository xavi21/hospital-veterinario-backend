package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "consulta")
public class Consulta extends Bitacora {

    @Id
    @GenericGenerator(name = "consulta", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "consulta", value = "sequence_consulta")})
    @GeneratedValue(generator = "secuence_consulta", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_consulta", allocationSize = 1)
    private Integer idconsulta;

    private Integer idcita;
    private Integer idempleado;
    @NotBlank
    @Size(max = 300)
    private String sintomas;

    @NotBlank
    @Size(max = 300)
    private String diagnostico;

    public Integer getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public @NotBlank @Size(max = 300) String getSintomas() {
        return sintomas;
    }

    public void setSintomas(@NotBlank @Size(max = 300) String sintomas) {
        this.sintomas = sintomas;
    }

    public @NotBlank @Size(max = 300) String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(@NotBlank @Size(max = 300) String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
