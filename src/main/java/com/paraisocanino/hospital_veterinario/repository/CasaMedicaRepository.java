package com.paraisocanino.hospital_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paraisocanino.hospital_veterinario.models.CasaMedica;

public interface CasaMedicaRepository extends JpaRepository<CasaMedica, Integer> {
}
