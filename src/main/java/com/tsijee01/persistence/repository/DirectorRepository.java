package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Director;

@Repository
public interface DirectorRepository extends BaseRepository <Director, Long>{

	Optional<Director> findByNombreAndApellido(String nombre, String apellido);

	List<Director> findAllByOrderByApellidoAscNombreAsc();
	
}
