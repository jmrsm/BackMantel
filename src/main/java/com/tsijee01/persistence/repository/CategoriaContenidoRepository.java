package com.tsijee01.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Categoria;

@Repository
public interface CategoriaContenidoRepository extends BaseRepository<Categoria, Long>{

	Optional<Categoria> findByNombreCategoria(String nombre);
	
}
