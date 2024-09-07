package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

}
