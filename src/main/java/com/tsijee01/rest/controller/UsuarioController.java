package com.tsijee01.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService userService;

	@RequestMapping(path = "api/public/altaUsuario", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "nombre", required = true) String nombre,
			@RequestParam(name = "apellido", required = true) String apellido) {

		if (userService.crearUser(email, password, nombre, apellido)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}

	}
	
	@RequestMapping(path = "api/usuario/listarFavoritos", method = RequestMethod.GET)
	public ResponseEntity<?> listarFavoritos(HttpServletRequest request,
			@RequestParam(name = "id", required = true) Long id) {

		List<Contenido> listarFavoritos = userService.listarFavoritos(id);
			return new ResponseEntity<List<Contenido>>(listarFavoritos, HttpStatus.OK);

	}
}
