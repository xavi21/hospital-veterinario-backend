package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface MedicamentoProjection {

    Integer getidmedicamento();

    String getnombre();

    String getdescripcion();

    Integer getidcasamedica();

    String getnombre_casa_medica();

    String getnombrecomercial();

    Integer getidcomponenteprincipal();

    String getnombre_componente_principal();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();
}
