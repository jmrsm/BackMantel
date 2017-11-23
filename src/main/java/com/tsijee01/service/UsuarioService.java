
package com.tsijee01.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	Page<Usuario> listarUsuarios(Pageable pag);

	Page<Usuario> buscarPorMail(Pageable pag, String query);

	Usuario buscarPorId(Long usuarioId);

	boolean crearSuscripcion(String email, String paypalToken);
}
