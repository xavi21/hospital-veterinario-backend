package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CitaProjection {

    Integer getidcita();

    LocalDateTime getfechahora();

    Integer getidmascota();

    String getnombre_mascota();

    Integer getidstatuscita();

    String getnombre_status_cita();

    String getmotivo();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();

}
