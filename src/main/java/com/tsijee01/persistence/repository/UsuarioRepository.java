package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Usuario;


@Repository
public interface UsuarioRepository extends BaseRepository <Usuario, Long>{

	List<Usuario> findByHabilitadoTrue();
	

}
