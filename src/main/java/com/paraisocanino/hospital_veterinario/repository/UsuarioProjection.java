package com.paraisocanino.hospital_veterinario.repository;

import java.time.LocalDate;

public interface UsuarioProjection {

    String getidusuario();

    Integer getidstatususuario();

    String getstatus_usuario();

    Integer getintentosdeacceso();

    String getpassword();

    String getsesionactual();

    String gettelefonomovil();

    LocalDate getultimafechacambiopassword();

    LocalDate getultimafechaingreso();

    Integer getidsucursal();

    String getsucursal();

    String getusuariocreacion();

    LocalDate getfechacreacion();

    String getusuariomodificacion();

    LocalDate getfechamodificacion();
}
