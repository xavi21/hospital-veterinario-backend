package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "opcion")
public class Opcion extends Bitacora {

    @Id
    @GenericGenerator(name = "opcion", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "opcion", value = "sequence_opcion")})
    @GeneratedValue(generator = "secuence_opcion", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_opcion", allocationSize = 1)
    private Integer idopcion;

    @NotBlank
    @Size(max = 50)
    private String name;

    private Integer ordenmenu;

    @NotBlank
    @Size(max = 100)
    private String pagina;

    public Integer getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Integer idopcion) {
        this.idopcion = idopcion;
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

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
}
