package com.paraisocanino.hospital_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paraisocanino.hospital_veterinario.models.TipoMascota;

public interface TipoMascotaReponsitory extends JpaRepository<TipoMascota, Integer> {
}
