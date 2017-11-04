package com.tsijee01.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.rest.dto.CategoriaDTO;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.rest.dto.SearchContenidoOmbdapi;
import com.tsijee01.service.ContenidoService;
import com.tsijee01.util.PageUtils;

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
	public ResponseEntity<?> altaCategoriaContenido(HttpServletRequest request, @RequestBody ContenidoDTO contenido) {
		if (contenidoService.altaContenido(contenido)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

	// buscar contenido en Omdb para los admin
	@RequestMapping(path = "api/admin/contenidoOmdb", method = RequestMethod.GET)
	public ResponseEntity<SearchContenidoOmbdapi> buscarContenidoOmdb(HttpServletRequest request,
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

	@RequestMapping(path = "api/admin/destacado", method = RequestMethod.POST)
	public ResponseEntity<?> marcarDestacado(HttpServletRequest request,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId,
			@RequestParam(name = "esDestacado", required = true) Boolean esDestacado) {

		contenidoService.marcarDestacado(contenidoId, esDestacado);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	// buscar contenido en Omdb para los admin
	@RequestMapping(path = "api/admin/tiposContenido", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> listarTiposContenido(HttpServletRequest request) {

		return new ResponseEntity<List<CategoriaDTO>>(
				mapper.mapAsList(contenidoService.obtenerTiposContenido(), CategoriaDTO.class), HttpStatus.OK);
	}

	@RequestMapping(path = "api/usuario/categoria", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarContenido(HttpServletRequest request) {

		List<CategoriaDTO> categorias = mapper.mapAsList(contenidoService.listarCategorias(), CategoriaDTO.class);
		return new ResponseEntity<List<CategoriaDTO>>(categorias, HttpStatus.OK);

	}

	// buscar contenido usuario final
	@RequestMapping(path = "api/usuario/pelicula", method = RequestMethod.GET)
	public ResponseEntity<Page<Pelicula>> buscarContenido(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query) {

		Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
		Page<Pelicula> pelis = contenidoService.buscarPelicula(pag, query);
		return new ResponseEntity<Page<Pelicula>>(pelis, HttpStatus.OK);

	}

	@RequestMapping(path = "api/admin/contenidoOmdb", method = RequestMethod.POST)
	public ResponseEntity<Long> altaContenido(HttpServletRequest request,
			@RequestParam(name = "proveedorContenidoId", required = true) Long proveedorContenidoId,
			@RequestParam(name = "omdbId", required = true) String omdbId,
			@RequestParam(name = "path", required = true) String path,
			@RequestParam(name = "esSerie", required = true) Boolean esSerie,
			@RequestParam(name = "esDestacado", required = true) Boolean esDestacado) {
		ContenidoDTO cont = this.getContenidoOmdbById(omdbId);

		Long id = null;
		if (esSerie) {
			id = contenidoService.altaSerie(mapper.map(cont, Serie.class), proveedorContenidoId, esDestacado);
		} else {
			id = contenidoService.altaPelicula(mapper.map(cont, Pelicula.class), proveedorContenidoId, path,
					esDestacado);
		}
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	private ContenidoDTO getContenidoOmdbById(String omdbId) {

		ContenidoDTO contenido = restTemplate
				.getForObject("http://www.omdbapi.com/?i=" + omdbId + "&apikey=" + omdbapikey, ContenidoDTO.class);
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

	@RequestMapping(path = "api/usuario/listarPorGenero", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> listarPorGenero(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query,
			@RequestParam(name = "generoId", required = true) Long generoId) {

		Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
		Page<Pelicula> pelis = contenidoService.buscarPeliculaPorGenero(pag, generoId);
		Page<ContenidoDTO> dtoPage = pelis.map(new Converter<Pelicula, ContenidoDTO>() {
			@Override
			public ContenidoDTO convert(Pelicula peli) {
				return mapper.map(peli, ContenidoDTO.class);
			}
		});
		return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
	}

	@RequestMapping(path = "api/usuario/listarPorActor", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> listarPorActor(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query,
			@RequestParam(name = "actorId", required = true) Long actorId) {

		Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
		Page<Pelicula> pelis = contenidoService.buscarPeliculaPorActor(pag, actorId);
		Page<ContenidoDTO> dtoPage = pelis.map(new Converter<Pelicula, ContenidoDTO>() {
			@Override
			public ContenidoDTO convert(Pelicula peli) {
				return mapper.map(peli, ContenidoDTO.class);
			}
		});
		return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
	}

	@RequestMapping(path = "api/usuario/listarPorDirector", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> listarPorDirector(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query,
			@RequestParam(name = "directorId", required = true) Long directorId) {

		Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
		Page<Pelicula> pelis = contenidoService.buscarPeliculaPorDirector(pag, directorId);
		Page<ContenidoDTO> dtoPage = pelis.map(new Converter<Pelicula, ContenidoDTO>() {
			@Override
			public ContenidoDTO convert(Pelicula peli) {
				return mapper.map(peli, ContenidoDTO.class);
			}
		});
		return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
	}
	
	@RequestMapping(path = "api/admin/listarmicontenido", method = RequestMethod.GET)
	public ResponseEntity<List<ContenidoDTO>> listarmicontenido(HttpServletRequest request,
			@RequestParam(name = "email", required = false) String email) {

		List<ContenidoDTO> contenidos = mapper.mapAsList(contenidoService.listarCategorias(), ContenidoDTO.class);
		return new ResponseEntity<List<ContenidoDTO>>(contenidos, HttpStatus.OK);

	}

	// TODO crear una api que reciba el contenido que está viendo un usuario
	// junto al tiempo de reproducción
	@RequestMapping(path = "api/usuario/guardarReproduccion", method = RequestMethod.PUT)
	public ResponseEntity<?> guardarReproduccion(HttpServletRequest request,
			@RequestParam(name = "idUsuario", required = true) Long idUsuario,
			@RequestParam(name = "idContenido", required = true) Long idContenido,
			@RequestParam(name = "Tiempo", required = true) Long tiempo) {
		
		contenidoService.guardarReproduccion(idUsuario, idContenido, tiempo); 
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(path = "api/usuario/favorito", method = RequestMethod.POST)
	public ResponseEntity<?> marcarFavorito(HttpServletRequest request,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId,
			@RequestParam(name = "usuarioId", required = true) Long usuarioId,
			@RequestParam(name = "esFavorito", required = true) Boolean esFavorito) {

		contenidoService.marcarFavorito(contenidoId, esFavorito, usuarioId);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}
}
