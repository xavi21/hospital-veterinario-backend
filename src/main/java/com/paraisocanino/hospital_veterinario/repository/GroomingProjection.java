package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface GroomingProjection {

    Integer getidcita();

    String getnombre_mascota();

    String getnombre_status_cita();

    String getmotivo();

    LocalDateTime getfechahora();

    Integer getidempleado();

    String getnombre_empleado();

    String getapellido_empleado();

    String getnombre_puesto();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();
}
