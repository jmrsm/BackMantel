package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.Director;
import com.tsijee01.rest.dto.DirectorDTO;
import com.tsijee01.service.DirectorService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class DirectorController {

	@Autowired
	private MapperFacade mapper;

	@Autowired
	private DirectorService directorService;

	// crear nuevo director en el sistema por ahora no se considera el caso de que
	// ya venga con contenido
	@RequestMapping(path = "api/admin/director", method = RequestMethod.POST)
	public ResponseEntity<?> altaDirector(HttpServletRequest request, @RequestBody DirectorDTO director) {

		String mailAdmin = (String) request.getSession()
				.getAttribute("TENANT_ADMIN");
		if (mailAdmin==null){
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		if (directorService.altaDirector(mapper.map(director, Director.class))) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}
	
	// obtener todos los directores en el sistema
	@RequestMapping(path = "api/admin/director", method = RequestMethod.GET)
	public ResponseEntity<?> listarDirectores(HttpServletRequest request) {

		String mailAdmin = (String) request.getSession()
				.getAttribute("TENANT_ADMIN");
		if (mailAdmin==null){
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Object>(mapper.mapAsList(directorService.findAll(), DirectorDTO.class), HttpStatus.OK);
	}

}
