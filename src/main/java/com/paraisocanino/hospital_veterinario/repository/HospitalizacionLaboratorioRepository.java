package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.HospitalizacionLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalizacionLaboratorioRepository extends JpaRepository<HospitalizacionLaboratorio, Integer> {

    @Query(value = "SELECT * FROM hospitalizacion_laboratorio_view", nativeQuery = true)
    List<HospitalizacionLaboratorioProjection> findAllHospitalLaboratorio();

    @Query(value = "SELECT * FROM hospitalizacion_laboratorio_view WHERE idhospitalizacion = :idhospitalizacion", nativeQuery = true)
    List<HospitalizacionLaboratorioProjection> findAllHospitalByLaboratorio(@Param("idhospitalizacion") Integer idhospitalizacion);
}
