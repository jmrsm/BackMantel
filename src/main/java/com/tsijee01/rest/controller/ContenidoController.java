package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
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

	@Autowired
	RestTemplate restTemplate;

	@Value("${omdbapikey}")
	private String omdbapikey;

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
	@RequestMapping(path = "api/admin/contenidoOmdb", method = RequestMethod.GET)
	public ResponseEntity<SearchContenidoOmbdapi> buscarContenido(HttpServletRequest request,
			@RequestParam(name = "nombre", required = true) String nombre,
			@RequestParam(name = "year", required = false) String year) {

		nombre = nombre.replace(" ", "+");
		if (year == null || year.isEmpty()) {

			return new ResponseEntity<SearchContenidoOmbdapi>(this.getContenidoByName(nombre), HttpStatus.OK);
		} else {
			return new ResponseEntity<SearchContenidoOmbdapi>(this.getContenidoByNameAndYear(nombre, year),
					HttpStatus.OK);
		}

	}

	// asociar contenido de omdb a un proveedor de contenido e insertarlo en la
	// base de datos
	@RequestMapping(path = "api/admin/guardarContenidoOmdb", method = RequestMethod.POST)
	public ResponseEntity<?> buscarContenido(HttpServletRequest request,
			@RequestParam(name = "proveedorContenidoId", required = true) Long ProveedorContenidoId,
			@RequestParam(name = "omdbId", required = true) String omdbId,
			@RequestParam(name = "temporada", required = false) int temporada,
			@RequestParam(name = "capitulo", required = false) int capitulo) {

		ContenidoOMDbDTO cont = this.getContenidoOmdbById(omdbId);
		if (temporada != 0 && capitulo != 0) {
			contenidoService.altaSerie(mapper.map(cont, Serie.class), temporada, capitulo);
		} else {
			contenidoService.altaPelicula(mapper.map(cont, Pelicula.class));
		}

		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	private ContenidoOMDbDTO getContenidoOmdbById(String omdbId) {

		ContenidoOMDbDTO contenido = restTemplate
				.getForObject("http://www.omdbapi.com/?i=" + omdbId + "&apikey=" + omdbapikey, ContenidoOMDbDTO.class);
		return contenido;

	}

	private SearchContenidoOmbdapi getContenidoByName(String nombre) {

		SearchContenidoOmbdapi contenido = restTemplate.getForObject(
				"http://www.omdbapi.com/?s=" + nombre + "&apikey=" + omdbapikey, SearchContenidoOmbdapi.class);
		return contenido;

	}

	private SearchContenidoOmbdapi getContenidoByNameAndYear(String nombre, String year) {

		SearchContenidoOmbdapi contenido = restTemplate.getForObject(
				"http://www.omdbapi.com/?s=" + nombre + "&y=" + year + "&apikey=" + omdbapikey,
				SearchContenidoOmbdapi.class);
		return contenido;

	}

}
