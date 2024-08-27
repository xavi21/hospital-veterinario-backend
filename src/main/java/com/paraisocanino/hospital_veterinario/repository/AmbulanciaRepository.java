package com.paraisocanino.hospital_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paraisocanino.hospital_veterinario.models.Ambulancia;

public interface AmbulanciaRepository extends JpaRepository<Ambulancia, Integer> {
}
