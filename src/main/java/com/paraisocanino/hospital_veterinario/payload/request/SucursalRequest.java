package com.paraisocanino.hospital_veterinario.payload.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SucursalRequest {

    private Integer idsucursal;

    @NotBlank
    private String name;

    @NotBlank
    private String direccion;

}
