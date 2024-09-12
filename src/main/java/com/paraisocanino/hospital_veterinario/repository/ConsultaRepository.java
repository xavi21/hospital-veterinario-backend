package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    @Query(value = "SELECT * FROM consulta_view", nativeQuery = true)
    List<ConsultaProjection> findAllConsulta();
}
