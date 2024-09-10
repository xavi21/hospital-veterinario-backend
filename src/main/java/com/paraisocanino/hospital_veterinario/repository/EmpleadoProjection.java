package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface EmpleadoProjection {

    Integer getidempleado();

    String getnombre_empleado();

    String getapellido_empleado();

    LocalDate getfechanacimiento();

    LocalDate getfechacontratacion();

    Integer getidestadocivil();

    String getnombre_estado_civil();

    Integer getidgenero();

    String getnombre_genero();

    Integer getidpuesto();

    String getnombre_puesto();

    Integer getidstatusempleado();

    String getnombre_status_empleado();

    Integer getidsucursal();

    String getnombre_sucursal();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();
}
