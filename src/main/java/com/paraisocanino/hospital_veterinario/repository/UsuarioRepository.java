package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}