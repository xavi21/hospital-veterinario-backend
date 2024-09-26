package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface HospitalizacionMedicamentoProjection {

    Integer getidhospitalizacionmedicamento();

    Integer getidhospitalizacion();

    Integer getidmedicamento();

    String getnombre();

    String getnombrecomercial();

    String getnombre_casa_medica();

    String getnombre_componente_principal();

    String getobservaciones();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();
}
