package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface PersonaProjection {

    Integer getidpersona();

    String getnombre();

    String getapellido();

    String gettelefono();

    LocalDate getfechanacimiento();

    String getdireccion();

    String getcorreoelectronico();

    Integer getid_estado_civil();

    String getnombre_estado_civil();

    Integer getid_genero();

    String getnombre_genero();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();


}
