package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ConsultaProjection {

    Integer getidconsulta();

    Integer getidcita();

    String getnombre_mascota();

    LocalDateTime getfechahora();

    String getmotivo();

    Integer getidempleado();

    String getnombre_empleado();

    String getapellido_empleado();

    String getsintomas();

    String getdiagnostico();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();

}
