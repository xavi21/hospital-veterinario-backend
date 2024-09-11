package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
public class Cita extends Bitacora {
    @Id
    @GenericGenerator(name = "cita", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "cita", value = "sequence_cita")})
    @GeneratedValue(generator = "secuence_cita", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_cita", allocationSize = 1)
    private Integer idcita;

    private LocalDateTime fechahora;

    private Integer idmascota;

    private Integer idstatuscita;

    @NotBlank
    @Size(max = 300)
    private String motivo;

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public Integer getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(Integer idmascota) {
        this.idmascota = idmascota;
    }

    public Integer getIdstatuscita() {
        return idstatuscita;
    }

    public void setIdstatuscita(Integer idstatuscita) {
        this.idstatuscita = idstatuscita;
    }

    public @NotBlank @Size(max = 300) String getMotivo() {
        return motivo;
    }

    public void setMotivo(@NotBlank @Size(max = 300) String motivo) {
        this.motivo = motivo;
    }
}
