package com.paraisocanino.hospital_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paraisocanino.hospital_veterinario.models.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer>  {
}
