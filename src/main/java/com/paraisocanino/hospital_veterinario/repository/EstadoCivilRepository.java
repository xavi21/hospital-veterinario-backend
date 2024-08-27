package com.paraisocanino.hospital_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paraisocanino.hospital_veterinario.models.EstadoCivil;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {
}
