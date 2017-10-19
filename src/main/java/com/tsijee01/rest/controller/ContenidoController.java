package com.tsijee01.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.tsijee01.rest.dto.ContenidoFullDTO;
import com.tsijee01.rest.dto.ContenidoOMDbDTO;
import com.tsijee01.rest.dto.SearchContenidoOmbdapi;
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
	public ResponseEntity<?> altaCategoriaContenido(HttpServletRequest request,
			@RequestBody ContenidoFullDTO contenido) {

		if (contenidoService.altaContenido(contenido)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

	@PostMapping("api/admin/contenidoMultimadia")
	public ResponseEntity<Object> agregarImagenAEvento(HttpServletRequest request,
			@RequestParam("file") MultipartFile file, @RequestParam("contenidoId") Long contenidoId) {
		try {
			contenidoService.altaContenidoMultimedia(contenidoId, file);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}

	}

	// buscar contenido
	@RequestMapping(path = "api/admin/contenido", method = RequestMethod.GET)
	public ResponseEntity<SearchContenidoOmbdapi> buscarContenido(HttpServletRequest request,
			@RequestParam("nombre") String nombre) {
		return new ResponseEntity<SearchContenidoOmbdapi>(this.getContenido(nombre), HttpStatus.OK);
	}

	@Autowired
	RestTemplate restTemplate;

	private SearchContenidoOmbdapi getContenido(String nombre) {

		SearchContenidoOmbdapi contenido = restTemplate
				.getForObject("http://www.omdbapi.com/?s=" + nombre + "&apikey=5b9f72c6", SearchContenidoOmbdapi.class);
		return contenido;

	}

}
