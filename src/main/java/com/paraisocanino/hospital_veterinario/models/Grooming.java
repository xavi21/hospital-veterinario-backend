package com.paraisocanino.hospital_veterinario.models;


import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "grooming")
public class Grooming extends Bitacora {

    @Id
    @GenericGenerator(name = "grooming", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "grooming", value = "sequence_grooming")})
    @GeneratedValue(generator = "secuence_grooming", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_grooming", allocationSize = 1)
    private Integer idgrooming;

    private Integer idcita;

    private Integer idempleado;

    public Integer getIdgrooming() {
        return idgrooming;
    }

    public void setIdgrooming(Integer idgrooming) {
        this.idgrooming = idgrooming;
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
}
