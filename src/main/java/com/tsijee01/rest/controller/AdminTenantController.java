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

import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.service.AdminTenantService;
import com.tsijee01.service.ContenidoService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class AdminTenantController {
	
	@Autowired
	AdminTenantService adminService;
	
	@Autowired 
	ContenidoService contenidoService;
	
	@Autowired 
	MapperFacade mapper;
	
	//obtener id admin
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/getID", method = RequestMethod.GET)
	public ResponseEntity<?> listarActores(HttpServletRequest request ,@RequestParam(name = "email", required = true) String email) {
		
		String mailAdmin = (String) request.getSession()
				.getAttribute("TENANT_ADMIN");
		if (mailAdmin==null){
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Object>(adminService.idUser(email), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/listarmicontenido", method = RequestMethod.GET)
	public ResponseEntity<List<ContenidoDTO>> listarmicontenido(HttpServletRequest request,
			@RequestParam(name = "email", required = false) String email) {
		//Manejo de sesión
		String mailAdmin = (String) request.getSession()
				.getAttribute("TENANT_ADMIN");
		if (mailAdmin==null){
			return new ResponseEntity<List<ContenidoDTO>>(HttpStatus.FORBIDDEN);
		}
		
		List<ContenidoDTO> contenidos = contenidoService.listarContenidoProveedor(email);
		return new ResponseEntity<List<ContenidoDTO>>(contenidos, HttpStatus.OK);

	}
}
