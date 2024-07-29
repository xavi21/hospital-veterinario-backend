package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.StatusUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUsuarioRepository extends JpaRepository<StatusUsuario, Integer> {
}
