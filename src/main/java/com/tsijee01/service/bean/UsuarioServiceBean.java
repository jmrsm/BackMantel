package com.tsijee01.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.UsuarioService;

@Service
public class UsuarioServiceBean implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findHabilitadosConUltimoPago() {
		return usuarioRepository.findByHabilitadoTrue();
	}

	@Override
	public List<Usuario> altaUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
