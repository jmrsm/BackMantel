package com.tsijee01.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Director;

@Repository
public interface DirectorRepository extends BaseRepository <Director, Long>{

	Optional<Director> findByNombreCompleto(String nombreCompleto);
	
}
