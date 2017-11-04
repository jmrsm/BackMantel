package com.tsijee01.service;

import java.util.List;

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Usuario;

public interface UsuarioService {

	List<Usuario> findAll();
	
	List<Usuario> findHabilitadosConUltimoPago();
	
	List<Usuario> altaUsuario(Usuario u);
	
	String existeUser(String email);
	
	boolean login(String email,String password);
	
	boolean crearUser(String email,String password,String nombre,String apellido);

	List<Contenido> listarFavoritos(Long id);
}
