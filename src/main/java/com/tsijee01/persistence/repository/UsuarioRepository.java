package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Usuario;



@Repository
public interface UsuarioRepository extends BaseRepository <Usuario, Long>{

	List<Usuario> findByHabilitadoTrue();
	boolean existsByEmail(String email);
	Optional<Usuario> findOneByEmail(String email);
}
