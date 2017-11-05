package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Usuario;



@Repository
public interface UsuarioRepository extends BaseRepository <Usuario, Long>{

	List<Usuario> findByHabilitadoTrue();
	
	boolean existsByEmail(String email);
	
	Optional<Usuario> findOneByEmail(String email);
	
	Optional<Usuario> findByEmail(String email); 
	
	Page<Usuario> findByEmailContaining(Pageable p,String busqueda );
}
