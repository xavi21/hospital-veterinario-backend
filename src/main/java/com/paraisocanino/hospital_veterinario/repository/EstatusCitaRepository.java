package com.paraisocanino.hospital_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paraisocanino.hospital_veterinario.models.EstatusCita;


public interface EstatusCitaRepository extends JpaRepository<EstatusCita, Integer> {

}
