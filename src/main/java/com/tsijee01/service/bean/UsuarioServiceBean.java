package com.tsijee01.service.bean;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.UsuarioService;
import com.tsijee01.util.Password;

@Service
public class UsuarioServiceBean implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository; 
	@Autowired
	private Password passwordUtil;
	
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
	
	@Override
	public String existeUser(String email) {
		// TODO Auto-generated method stub
		if(usuarioRepository.existsByEmail(email)){
			return "Existe";
		}else{
			return "No existe";
		}
			
		
	}
	public boolean login(String email, String password) {

		Optional<Usuario> sa = usuarioRepository.findOneByEmail(email);
		if (sa.isPresent()) {
			if (passwordUtil.checkearPassword(password, sa.get().getPassowd())){
				// TODO loguear el usuario al sistema 
				return true;
			}
			
		}
		return false;
	}
}
