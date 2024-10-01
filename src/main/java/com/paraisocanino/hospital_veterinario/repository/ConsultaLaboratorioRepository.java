package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.ConsultaLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaLaboratorioRepository extends JpaRepository<ConsultaLaboratorio, Integer> {

    @Query(value = "SELECT * FROM consulta_laboratorio_view", nativeQuery = true)
    List<ConsultaLaboratorioProjection> findAllConsultaLaboratorio();

    @Query(value = "SELECT * FROM consulta_laboratorio_view WHERE idconsulta = :idconsulta", nativeQuery = true)
    List<ConsultaLaboratorioProjection> findAllLaboratorioByConsulta(@Param("idconsulta") Integer idconsulta);
}
