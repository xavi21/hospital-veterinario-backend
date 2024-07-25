package com.paraisocanino.hospital_veterinario.payload.response;

import com.paraisocanino.hospital_veterinario.models.Sucursal;

import java.time.LocalDate;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String idusuario;
    private LocalDate ultimafechaingreso;
    private Integer intentosdeacceso;
    private String sesionactual;
    private LocalDate ultimafechacambiopassword;
    private String telefonomovil;
    private Integer idstatususuario;
    private Integer idsucursal;

    public JwtResponse(String accessToken, String idusuario, LocalDate ultimafechaingreso, Integer intentosdeacces,
                       String sesionactual, LocalDate ultimafechacambiopassword, String telefonomovi, Integer idstatususuario, Integer idsucursal) {
        this.token = accessToken;
        this.idusuario = idusuario;
        this.ultimafechaingreso = ultimafechaingreso;
        this.intentosdeacceso = intentosdeacces;
        this.sesionactual = sesionactual;
        this.ultimafechacambiopassword = ultimafechacambiopassword;
        this.telefonomovil = telefonomovi;
        this.idstatususuario = idstatususuario;
        this.idsucursal = idsucursal;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public LocalDate getUltimafechaingreso() {
        return ultimafechaingreso;
    }

    public void setUltimafechaingreso(LocalDate ultimafechaingreso) {
        this.ultimafechaingreso = ultimafechaingreso;
    }

    public Integer getIntentosdeacceso() {
        return intentosdeacceso;
    }

    public void setIntentosdeacceso(Integer intentosdeacceso) {
        this.intentosdeacceso = intentosdeacceso;
    }

    public String getSesionactual() {
        return sesionactual;
    }

    public void setSesionactual(String sesionactual) {
        this.sesionactual = sesionactual;
    }

    public LocalDate getUltimafechacambiopassword() {
        return ultimafechacambiopassword;
    }

    public void setUltimafechacambiopassword(LocalDate ultimafechacambiopassword) {
        this.ultimafechacambiopassword = ultimafechacambiopassword;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public Integer getIdstatususuario() {
        return idstatususuario;
    }

    public void setIdstatususuario(Integer idstatususuario) {
        this.idstatususuario = idstatususuario;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }
}
