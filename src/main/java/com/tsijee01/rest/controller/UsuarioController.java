package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.service.UsuarioService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService userService;

	@Autowired
	private MapperFacade mepper;

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
}
