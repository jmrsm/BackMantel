package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.service.AdminTenantService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class AdminTenantController {
	
	@Autowired
	private AdminTenantService admintenantservice;
	
	@Autowired
	private MapperFacade mepper;
	
	@RequestMapping(path = "api/adminTenant/altaContenido", method = RequestMethod.GET)
	public ResponseEntity<?> existeUser(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email) {
		return new ResponseEntity<String>(admintenantservice.existeUser(email),HttpStatus.OK);
	}

}
