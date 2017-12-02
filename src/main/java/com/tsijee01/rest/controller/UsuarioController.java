package com.tsijee01.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.CapituloSerie;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.service.UsuarioService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService userService;

	@Autowired
	private MapperFacade mapper;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/public/altaUsuario", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "nombre", required = true) String nombre,
			@RequestParam(name = "apellido", required = true) String apellido) {

		if (userService.crearUser(email, password, nombre, apellido,null)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/public/suscripción", method = RequestMethod.POST)
	public ResponseEntity<?> crearSubscripcionUsuario(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "paypalToken", required = true) String paypalToken) {

		if (userService.crearSuscripcion(email, paypalToken)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarFavoritos", method = RequestMethod.GET)
	public ResponseEntity<List<ContenidoDTO>> listarFavoritos(HttpServletRequest request,
			@RequestParam(name = "usuarioId", required = true) Long id) {
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<List<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		List<Contenido> listarFavoritos = userService.listarFavoritos(id);
		return new ResponseEntity<List<ContenidoDTO>>(mapper.mapAsList(listarFavoritos, ContenidoDTO.class),
				HttpStatus.OK);

	}
	
}
