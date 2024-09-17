package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.DetalleReceta;
import com.paraisocanino.hospital_veterinario.payload.DetalleRecetaCompuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallerecetaRepository extends JpaRepository<DetalleReceta, DetalleRecetaCompuesta> {

    @Query(value = "SELECT * FROM detalle_receta_view", nativeQuery = true)
    List<DetalleRecetaProjection> findAllDetalleReceta();

    @Query(value = "SELECT * FROM detalle_receta_view WHERE idreceta = :idreceta", nativeQuery = true)
    List<DetalleRecetaProjection> findMeciamentobyReceta(@Param("idreceta") Integer idreceta);
}
