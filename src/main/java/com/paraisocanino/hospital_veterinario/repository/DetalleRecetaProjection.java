package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface DetalleRecetaProjection {

    Integer getidreceta();

    Integer getidmedicamento();

    String getnombre_medicamento();

    String getnombre_casa_medica();

    String getnombrecomercial();

    String getnombre_componente_principal();

    Integer getcantidad();

    String getindicaciones();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();
}
