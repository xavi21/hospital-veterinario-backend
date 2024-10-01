package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "consulta_laboratorio")
public class ConsultaLaboratorio extends Bitacora {

    @Id
    @GenericGenerator(name = "consulta_laboratorio", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "consulta_laboratorio", value = "sequence_consulta_laboratorio")})
    @GeneratedValue(generator = "secuence_consulta_laboratorio", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_consulta_laboratorio", allocationSize = 1)
    private Integer idconsultalaboratorio;

    private Integer idconsulta;
    private Integer idlaboratorio;
    private LocalDate fechasolicitud;
    private LocalDate fecharesultado;
    @Column(columnDefinition = "TEXT")
    private String resultado;

    public Integer getIdconsultalaboratorio() {
        return idconsultalaboratorio;
    }

    public void setIdconsultalaboratorio(Integer idconsultalaboratorio) {
        this.idconsultalaboratorio = idconsultalaboratorio;
    }

    public Integer getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public Integer getIdlaboratorio() {
        return idlaboratorio;
    }

    public void setIdlaboratorio(Integer idlaboratorio) {
        this.idlaboratorio = idlaboratorio;
    }

    public LocalDate getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(LocalDate fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public LocalDate getFecharesultado() {
        return fecharesultado;
    }

    public void setFecharesultado(LocalDate fecharesultado) {
        this.fecharesultado = fecharesultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
