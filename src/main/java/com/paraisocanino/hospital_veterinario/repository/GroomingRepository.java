package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.Grooming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroomingRepository extends JpaRepository<Grooming, Integer> {
    @Query(value = "SELECT * FROM grooming_view", nativeQuery = true)
    List<GroomingProjection> findAllGrooming();
}
