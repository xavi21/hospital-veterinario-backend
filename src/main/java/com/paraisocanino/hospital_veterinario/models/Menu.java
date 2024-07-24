package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "menu")
public class Menu  extends Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmenu;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private Integer ordenmenu;


    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdenmenu() {
        return ordenmenu;
    }

    public void setOrdenmenu(Integer ordenmenu) {
        this.ordenmenu = ordenmenu;
    }
}
