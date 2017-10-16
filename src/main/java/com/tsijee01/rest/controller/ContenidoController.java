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

import com.tsijee01.rest.dto.ContenidoFullDTO;
import com.tsijee01.service.ContenidoService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class ContenidoController {

	@Autowired
	private ContenidoService contenidoService;

	@Autowired
	private MapperFacade mapper;

	// crear nuevo contenido
	@RequestMapping(path = "api/admin/contenido", method = RequestMethod.POST)
	public ResponseEntity<?> altaCategoriaContenido(HttpServletRequest request, @RequestBody ContenidoFullDTO contenido) {

		if (contenidoService.altaContenido(contenido)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

}
