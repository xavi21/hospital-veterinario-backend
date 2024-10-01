package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface ConsultaLaboratorioProjection {

    Integer getidconsultalaboratorio();

    Integer getidconsulta();

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
