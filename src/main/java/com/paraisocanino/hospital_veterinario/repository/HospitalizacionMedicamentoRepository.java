package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.HospitalizacionMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalizacionMedicamentoRepository extends JpaRepository<HospitalizacionMedicamento, Integer> {

    @Query(value = "SELECT * FROM hospitalizacion_medicamento_view", nativeQuery = true)
    List<HospitalizacionMedicamentoProjection> findAllHospitalMedicamento();

    @Query(value = "SELECT * FROM hospitalizacion_medicamento_view WHERE idhospitalizacion = :idhospitalizacion", nativeQuery = true)
    List<HospitalizacionMedicamentoProjection> findmedicamentoByHospitalizacion(@Param("idhospitalizacion") Integer idhospitalizacion);
}
