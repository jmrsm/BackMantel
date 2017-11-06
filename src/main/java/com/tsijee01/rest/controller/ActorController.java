package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

//prueba
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.Actor;
import com.tsijee01.rest.dto.ActorDTO;
import com.tsijee01.service.ActorService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class ActorController {

	@Autowired
	private MapperFacade mapper;

	@Autowired
	private ActorService actorService;

	// crear nuevo actor en el sistema por ahora no se considera el caso de que
	// ya venga con contenido
	@RequestMapping(path = "api/admin/actor", method = RequestMethod.POST)
	public ResponseEntity<?> altaActor(HttpServletRequest request, @RequestBody ActorDTO actor) {
		
		String mailAdmin = (String) request.getSession()
				.getAttribute("TENANT_ADMIN");
		if (mailAdmin==null){
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		if (actorService.altaActor(mapper.map(actor, Actor.class))) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}
	
	
	// obtener todos los actores en el sistema
	@RequestMapping(path = "api/admin/actor", method = RequestMethod.GET)
	public ResponseEntity<?> listarActores(HttpServletRequest request) {

		String mailAdmin = (String) request.getSession()
				.getAttribute("TENANT_ADMIN");
		if (mailAdmin==null){
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Object>(mapper.mapAsList(actorService.findAll(), ActorDTO.class), HttpStatus.OK);
	}

}
