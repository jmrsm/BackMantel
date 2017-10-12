package com.tsijee01.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.ProveedorContenido;


@Repository
public interface ProveedorContenidoRepository extends BaseRepository <ProveedorContenido, Long>{

	Optional<ProveedorContenido> findByNombre(String nombre);

	
}
