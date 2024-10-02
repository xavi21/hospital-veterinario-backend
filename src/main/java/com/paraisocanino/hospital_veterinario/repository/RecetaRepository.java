package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    @Query(value = "SELECT * FROM receta_view", nativeQuery = true)
    List<RecetaProjection> findAllReceta();

    @Query(value = "SELECT * FROM receta_view WHERE idconsulta = :idconsulta", nativeQuery = true)
    List<RecetaProjection> findmedicamentoByConsulta(@Param("idconsulta") Integer idconsulta);

}
