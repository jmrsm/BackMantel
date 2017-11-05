package com.tsijee01.service.bean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.ContenidoRepository;
import com.tsijee01.persistence.repository.HistorialContenidoRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.UsuarioService;
import com.tsijee01.util.Password;

@Service
public class UsuarioServiceBean implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private Password passwordUtil;
	
	@Autowired
	private HistorialContenidoRepository historialContenidoRepository; 
		
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
	@Override
	public boolean crearUser(String email, String password, String nombre, String apellido) {

		Optional<Usuario> sa = usuarioRepository.findOneByEmail(email);
		if(sa.isPresent()){
			//Si el usuario ya existe
			return false;
		}else{
			Usuario user= new Usuario();
			user.setApellido(apellido);
			user.setEmail(email);
			user.setHabilitado(true);
			user.setNombre(nombre);
			user.setPassowd(passwordUtil.hasherPassword(password));
			usuarioRepository.save(user);
			return true;
		}
		
	}

	@Override
	public List<Contenido> listarFavoritos(Long id) {
		Optional <Usuario> u = usuarioRepository.findOne(id);
		List<HistorialContenido> listaHC = historialContenidoRepository.findByFavoritoAndUsuario(true, u.get());
		return listaHC.stream().map(x->x.getContenido()).collect(Collectors.toList());
		
	}
}
