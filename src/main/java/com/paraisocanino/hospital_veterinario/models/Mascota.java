package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mascota")
public class Mascota  extends Bitacora {

    @Id
    @GenericGenerator(name = "mascota", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "mascota", value = "sequence_mascota")})
    @GeneratedValue(generator = "secuence_mascota", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_mascota", allocationSize = 1)
    private Integer idmascota;

    private Integer idTipoMascota;

    private Integer id_genero;

    private Integer idpersona;

    private Integer idColor;

    private Integer idTalla;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    private Integer peso;

    public Integer getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(Integer idmascota) {
        this.idmascota = idmascota;
    }

    public Integer getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public Integer getId_genero() {
        return id_genero;
    }

    public void setId_genero(Integer id_genero) {
        this.id_genero = id_genero;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public Integer getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public @NotBlank @Size(max = 100) String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank @Size(max = 100) String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}
