package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface HospitalizacionProjection {

    Integer getidhospitalizacion();

    Integer getidmascota();

    String getnombre_mascota();

    String getnombre_genero();

    String getnombre_talla();

    String getnombre_color();

    Integer getpeso();

    String getnombre_propietario();

    String getapellido_propietario();

    String getnombre_tipo_mascota();

    Integer getidjaula();

    String getdescripcion();

    LocalDate getfechaingreso();

    LocalDate getfechasalida();

    String getmotivo();

    String getobservaciones();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();

}
