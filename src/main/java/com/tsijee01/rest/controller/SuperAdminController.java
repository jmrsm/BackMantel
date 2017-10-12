package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.rest.dto.AdminTenantDTO;
import com.tsijee01.rest.dto.SuperAdminDTO;
import com.tsijee01.service.ProveedorContenidoService;
import com.tsijee01.service.SuperAdminService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class SuperAdminController {

	@Autowired
	private SuperAdminService administradorService;
	
	@Autowired
	private SuperAdminService superAdminService;
	
	@Autowired
	private ProveedorContenidoService proveedorContenidoService;
	
	@Autowired
	private MapperFacade mapper;

	// Esto no puede ser publica hay que borrarla antes de entregar
	@RequestMapping(path = "api/superAdmin/altaSuperAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> altaSuperAdministrador(HttpServletRequest request,
			@RequestBody SuperAdminDTO superAdminDTO ,
			@RequestParam(name = "password", required = true) String password) {

		if (superAdminService.altaSuperAdmin(mapper.map(superAdminDTO,SuperAdmin.class),password)){
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(path = "api/superAdmin/altaProveedorContenido", method = RequestMethod.POST)
	public ResponseEntity<?> altaAdministradorContenido(HttpServletRequest request,
			@RequestParam(name = "nombre", required = true) String nombreProveedorContenido) {

		if (proveedorContenidoService.altaProveedorContenido(nombreProveedorContenido)){
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}
	
	
	@RequestMapping(path = "api/superAdmin/altaAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> altaAdministradorTenant(HttpServletRequest request,
			@RequestBody AdminTenantDTO adminTenant ,
			@RequestParam(name = "password", required = true) String password ,
			@RequestParam(name = "proveedorContenidoId", required = true) Long proveedorContenidoId) {

		if (administradorService.altaAdmin(mapper.map(adminTenant,AdminTenant.class), proveedorContenidoId, password)){
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

}
