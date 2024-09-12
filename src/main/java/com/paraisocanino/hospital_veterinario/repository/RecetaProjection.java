package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface RecetaProjection {

    Integer getidreceta();

    Integer getidconsulta();

    String getnombre_mascota();

    String getnombre_empleado();

    String getapellido_empleado();

    String getobservaciones();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();
}
