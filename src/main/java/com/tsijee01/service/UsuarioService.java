package com.tsijee01.service;

import java.util.List;

import com.tsijee01.persistence.model.Usuario;

public interface UsuarioService {

	List<Usuario> findAll();
	
	List<Usuario> findHabilitadosConUltimoPago();
	
	List<Usuario> altaUsuario(Usuario u);
}
