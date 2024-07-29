package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "status_usuario")
public class StatusUsuario extends Bitacora {

    @Id
    @GenericGenerator(name = "status_usuario", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "status_usuario", value = "sequence_status_usuario") })
    @GeneratedValue(generator = "secuence_status_usuario", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_status_usuario", allocationSize=1)
    private Integer idstatususuario;

    @NotBlank
    @Size(max = 100)
    private String name;

    public Integer getIdstatususuario() {
        return idstatususuario;
    }

    public void setIdstatususuario(Integer idstatususuario) {
        this.idstatususuario = idstatususuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
