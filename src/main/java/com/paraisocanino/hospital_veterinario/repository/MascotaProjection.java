package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface MascotaProjection {
    Integer getidmascota();

    String getnombre_mascota();

    Integer getpeso();

    Integer getid_color();

    String getnombre_color();

    Integer getid_talla();

    String getnombre_talla();

    Integer getid_tipo_mascota();

    String getnombre_tipo_mascota();

    Integer getid_genero();

    String getnombre_genero();

    Integer getidpersona();

    String getnombre_propietario();

    String getapellido_propietario();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();

}
