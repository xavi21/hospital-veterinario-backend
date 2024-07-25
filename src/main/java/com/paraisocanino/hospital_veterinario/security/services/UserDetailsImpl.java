package com.paraisocanino.hospital_veterinario.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paraisocanino.hospital_veterinario.models.Sucursal;
import com.paraisocanino.hospital_veterinario.models.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String idusuario;

    private LocalDate ultimafechaingreso;

    private Integer intentosdeacceso;

    private String sesionactual;


    private LocalDate ultimafechacambiopassword;

    private String telefonomovil;

    private Integer idstatususuario;

    private Integer idsucursal;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String idusuario, LocalDate ultimafechaingreso, Integer intentosdeacces,
                           String sesionactual, LocalDate ultimafechacambiopassword, String telefonomovi, Integer idstatususuario, Integer idsucursal, String password) {
        this.idusuario = idusuario;
        this.ultimafechaingreso = ultimafechaingreso;
        this.intentosdeacceso = intentosdeacces;
        this.sesionactual = sesionactual;
        this.ultimafechacambiopassword = ultimafechacambiopassword;
        this.telefonomovil = telefonomovi;
        this.idstatususuario = idstatususuario;
        this.idsucursal = idsucursal;
        this.password = password;
    }

    public static UserDetailsImpl build(Usuario user) {

        return new UserDetailsImpl(
                user.getIdusuario(),
                user.getUltimafechaingreso(),
                user.getIntentosdeacceso(),
                user.getSesionactual(),
                user.getUltimafechacambiopassword(),
                user.getTelefonomovil(),
                user.getIdstatususuario(),
                user.getKeySucursal(),
                user.getPassword());
    }


    public String getIdusuario() {
        return idusuario;
    }

    public LocalDate getUltimafechaingreso() {
        return ultimafechaingreso;
    }

    public Integer getIntentosdeacceso() {
        return intentosdeacceso;
    }

    public String getSesionactual() {
        return sesionactual;
    }

    public LocalDate getUltimafechacambiopassword() {
        return ultimafechacambiopassword;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public Integer getIdstatususuario() {
        return idstatususuario;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return idusuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(idusuario, user.idusuario);
    }
}
