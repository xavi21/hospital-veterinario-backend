package com.paraisocanino.hospital_veterinario.models;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "sucursal")
public class Sucursal extends Bitacora {
    @Id
    @GenericGenerator(name = "sucursal", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "sucursal", value = "sequence_sucursal") })
    @GeneratedValue(generator = "secuence_sucursal", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_sucursal", allocationSize=1)
    private Integer idsucursal;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String direccion;

    @OneToMany(mappedBy = "idsucursal")
    private Set<Usuario> usuario;

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Set<Usuario> usuario) {
        this.usuario = usuario;
    }
}
