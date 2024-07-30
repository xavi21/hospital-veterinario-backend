package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "menu")
public class Menu  extends Bitacora {

    @Id
    @GenericGenerator(name = "menu", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "menu", value = "sequence_menu") })
    @GeneratedValue(generator = "secuence_menu", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_menu", allocationSize=1)
    private Integer idmenu;

    @NotBlank
    @Size(max = 50)
    private String name;

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
