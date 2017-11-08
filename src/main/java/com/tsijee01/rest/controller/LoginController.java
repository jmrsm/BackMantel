package com.tsijee01.rest.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.TipoUsuarioEnum;
import com.tsijee01.rest.dto.LoginDTO;
import com.tsijee01.service.LoginService.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(path = "api/public/login", method = RequestMethod.GET)
	public ResponseEntity<LoginDTO> loginAdministradorTenant(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {

		Optional<TipoUsuarioEnum> tUE = (loginService.login(email, password));
		if (tUE.isPresent()) {
			if (tUE.get().tipoUsuario().equals(TipoUsuarioEnum.Forbbiden.tipoUsuario())) {
				return new ResponseEntity<LoginDTO>(HttpStatus.FORBIDDEN);
			} else {
				
				LoginDTO lDTO = new LoginDTO();
				lDTO.setId(loginService.obtenerId(email));
				lDTO.setTipoUsuario(tUE.get().tipoUsuario());
				
				HttpSession sesion = request.getSession();
				if(tUE.get().equals(TipoUsuarioEnum.SUPER_ADMIN)){
					sesion.setAttribute("SUPER_ADMIN", Optional.of(new String(email)));	
				}else if(tUE.get().equals(TipoUsuarioEnum.TENANT_ADMIN)){
					sesion.setAttribute("TENANT_ADMIN",  Optional.of(new String(email)));		
				}else if (tUE.get().equals(TipoUsuarioEnum.USUARIO)){
					sesion.setAttribute("USUARIO",  Optional.of(new String(email)));	
				}
				return new ResponseEntity<LoginDTO>(lDTO, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<LoginDTO>(HttpStatus.NOT_FOUND);
		}

	}

}
