package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.UsuarioOpcion;
import com.paraisocanino.hospital_veterinario.payload.LlaveCompuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioOpcionRepository extends JpaRepository<UsuarioOpcion, LlaveCompuesta> {

    @Query(value = "SELECT * FROM usuario_menu_opcion", nativeQuery = true)
    List<UsuarioMenuOpcionProjection> findAllMenu();

    @Query(value = "SELECT * FROM usuario_menu_opcion WHERE idusuario = :idUsuario", nativeQuery = true)
    List<UsuarioMenuOpcionProjection> findMenuByUsuario(@Param("idUsuario") String idUsuario);
}
