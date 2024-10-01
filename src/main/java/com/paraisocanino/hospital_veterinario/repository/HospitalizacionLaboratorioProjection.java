package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface HospitalizacionLaboratorioProjection {

    Integer getidhospitalizacionlaboratorio();

    Integer getidhospitalizacion();

    Integer getidlaboratorio();

    String getnombre();

    String getdescripcion();

    String getresultado();

    LocalDate getfechasolicitud();

    LocalDate getfecharesultado();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();


}
