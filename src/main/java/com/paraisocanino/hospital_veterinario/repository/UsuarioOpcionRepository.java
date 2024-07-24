package com.paraisocanino.hospital_veterinario.repository;

import com.paraisocanino.hospital_veterinario.models.UsuarioOpcion;
import com.paraisocanino.hospital_veterinario.payload.LlaveCompuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioOpcionRepository extends JpaRepository<UsuarioOpcion, LlaveCompuesta> {
}
