package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.Hospitalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalizacionRepository extends JpaRepository<Hospitalizacion, Integer> {

    @Query(value = "SELECT * FROM hospitalizacion_view", nativeQuery = true)
    List<HospitalizacionProjection> findAllHospitalizacion();
}
