package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "status_usuario")
public class StatusUsuario extends Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public @NotBlank @Size(max = 100) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 100) String name) {
        this.name = name;
    }
}
