package com.paraisocanino.hospital_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paraisocanino.hospital_veterinario.models.Talla;

public interface TallaRepository extends JpaRepository<Talla, Integer> {
}
