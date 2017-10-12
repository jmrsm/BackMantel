package com.tsijee01.rest.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.TipoUsuarioEnum;
import com.tsijee01.service.LoginService.LoginService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class LoginController {

	@Autowired
	private MapperFacade mepper;

	@Autowired
	private LoginService loginService;

	// @RequestMapping(path = "/existAdmin/", method = RequestMethod.GET)
	// public ResponseEntity<?> existeUser(HttpServletRequest request,
	// @RequestParam(name = "email", required = true) String email) {
	//
	// return new
	// ResponseEntity<String>(admintenantservice.existeUser(email),HttpStatus.OK);
	//
	// }

	@RequestMapping(path = "api/public/login/", method = RequestMethod.GET)
	public ResponseEntity<String> loginAdministradorTenant(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {

		Optional<TipoUsuarioEnum> tUE = (loginService.login(email, password));
		if (tUE.isPresent()){
			if (tUE.get().tipoUsuario().equals(TipoUsuarioEnum.Forbbiden.tipoUsuario())){
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}else{
			return new ResponseEntity<String>(tUE.get().tipoUsuario(),HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		

	}

}
