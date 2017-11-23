package com.tsijee01.rest.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tsijee01.persistence.model.Evento;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.rest.dto.CategoriaDTO;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.rest.dto.SearchContenidoOmbdapi;
import com.tsijee01.service.AdminTenantService;
import com.tsijee01.service.ContenidoService;
import com.tsijee01.util.PageUtils;

import ma.glasnost.orika.MapperFacade;

@RestController
public class ContenidoController {

	@Autowired
	private ContenidoService contenidoService;

	@Autowired
	private AdminTenantService adminTenantService;
	
	@Autowired
	private MapperFacade mapper;

	@Autowired
	RestTemplate restTemplate;

	@Value("${omdbapikey}")
	private String omdbapikey;

	// crear nuevo contenido
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/contenido", method = RequestMethod.POST)
	public ResponseEntity<?> altaCategoriaContenido(HttpServletRequest request, @RequestBody ContenidoDTO contenido) {
		
//		String mailAdmin = (String) request.getSession().getAttribute("TENANT_ADMIN");
//		if (mailAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		if (contenidoService.altaContenido(contenido)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

	// buscar contenido en Omdb para los admin
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/contenidoOmdb", method = RequestMethod.GET)
	public ResponseEntity<SearchContenidoOmbdapi> buscarContenidoOmdb(HttpServletRequest request,
			@RequestParam(name = "nombre", required = true) String nombre,
			@RequestParam(name = "year", required = false) String year) {

//		String mailAdmin = (String) request.getSession().getAttribute("TENANT_ADMIN");
//		if (mailAdmin == null) {
//			return new ResponseEntity<SearchContenidoOmbdapi>(HttpStatus.FORBIDDEN);
//		}
		nombre = nombre.replace(" ", "+");
		if (year == null || year.isEmpty()) {
			return new ResponseEntity<SearchContenidoOmbdapi>(this.getContenidoByName(nombre), HttpStatus.OK);
		} else {
			return new ResponseEntity<SearchContenidoOmbdapi>(this.getContenidoByNameAndYear(nombre, year),
					HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/destacado", method = RequestMethod.POST)
	public ResponseEntity<?> marcarDestacado(HttpServletRequest request,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId,
			@RequestParam(name = "esDestacado", required = true) Boolean esDestacado) {
		
//		String mailAdmin = (String) request.getSession().getAttribute("TENANT_ADMIN");
//		if (mailAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		contenidoService.marcarDestacado(contenidoId, esDestacado);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	// buscar contenido en Omdb para los admin
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/tiposContenido", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> listarTiposContenido(HttpServletRequest request) {

//		String mailAdmin = (String) request.getSession().getAttribute("TENANT_ADMIN");
//		if (mailAdmin == null) {
//			return new ResponseEntity<List<CategoriaDTO>>(HttpStatus.FORBIDDEN);
//		}
		return new ResponseEntity<List<CategoriaDTO>>(
				mapper.mapAsList(contenidoService.obtenerTiposContenido(), CategoriaDTO.class), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/categoria", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarContenido(HttpServletRequest request) {

//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<List<CategoriaDTO>>(HttpStatus.FORBIDDEN);
//		}
		List<CategoriaDTO> categorias = mapper.mapAsList(contenidoService.listarCategorias(), CategoriaDTO.class);
		return new ResponseEntity<List<CategoriaDTO>>(categorias, HttpStatus.OK);

	}

	// buscar contenido usuario final
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarPeliculasConBusqueda", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> buscarPeliculas(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query) {
		
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Page<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		
		if (StringUtils.isEmpty(query)) {
			Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
			Page<Pelicula> pelis = contenidoService.listarPeliculas(pag);
			Page<ContenidoDTO> dtoPage = pelis.map(new Converter<Pelicula, ContenidoDTO>() {
				@Override
				public ContenidoDTO convert(Pelicula peli) {
					return mapper.map(peli, ContenidoDTO.class);
				}
			});
			return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
		}else {
			Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
			Page<Pelicula> pelis = contenidoService.buscarPelicula(pag, query);
			Page<ContenidoDTO> dtoPage = pelis.map(new Converter<Pelicula, ContenidoDTO>() {
				@Override
				public ContenidoDTO convert(Pelicula peli) {
					return mapper.map(peli, ContenidoDTO.class);
				}
			});
			return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
		}
		

	}
	

	// buscar contenido usuario final
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarSeriesConBusqueda", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> buscarSeries(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query) {
		
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Page<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		
		if (StringUtils.isEmpty(query)) {
			Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
			Page<Serie> series= contenidoService.listarSeries(pag);
			Page<ContenidoDTO> dtoPage = series.map(new Converter<Serie, ContenidoDTO>() {
				@Override
				public ContenidoDTO convert(Serie serie) {
					return mapper.map(serie, ContenidoDTO.class);
				}
			});
			return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
		}else {
			Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
			Page<Serie> series = contenidoService.buscarSerie(pag, query);
			Page<ContenidoDTO> dtoPage = series.map(new Converter<Serie, ContenidoDTO>() {
				@Override
				public ContenidoDTO convert(Serie serie) {
					return mapper.map(serie, ContenidoDTO.class);
				}
			});
			return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
		}
		

	}
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	// obtener todos los usuarios finales del sistema
	@RequestMapping(path = "api/usuario/listarEventosConBusqueda", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> obtenerEventosCOnBusqueda(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query) {

//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Page<UsuarioDTO>>(HttpStatus.FORBIDDEN);
//		}

		Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
		Page<ContenidoDTO> dtoPage;
		if (StringUtils.isEmpty(query)) {
			dtoPage = contenidoService.listarEventos(pag).map(new Converter<Evento, ContenidoDTO>() {
				@Override
				public ContenidoDTO convert(Evento ev) {
					return mapper.map(ev, ContenidoDTO.class);
				}
			});
		} else {
			dtoPage = contenidoService.listarEventosConBusqueda(pag, query).map(new Converter<Evento, ContenidoDTO>() {
				@Override
				public ContenidoDTO convert(Evento ev) {
					return mapper.map(ev, ContenidoDTO.class);
				}
			});
		}
		return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
	}

	// si ya la vio devuelve el último segundo visto sino 0
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/verContenido", method = RequestMethod.GET)
	public ResponseEntity<Long> verContenido(HttpServletRequest request,
			@RequestParam(name = "usuarioID", required = true) Long usuarioId,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId) {
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Long>(HttpStatus.FORBIDDEN);
//		}
		Long segundosVistos = contenidoService.verContenido(usuarioId, contenidoId);
		return new ResponseEntity<Long>(segundosVistos, HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/contenidoOmdb", method = RequestMethod.POST)
	public ResponseEntity<Long> altaContenido(HttpServletRequest request,
			@RequestParam(name = "proveedorContenidoId", required = true) Long proveedorContenidoId,
			@RequestParam(name = "omdbId", required = true) String omdbId,
			@RequestParam(name = "path", required = true) String path,
			@RequestParam(name = "esSerie", required = true) Boolean esSerie,
			@RequestParam(name = "esPago", required = true) Boolean esPago,
			@RequestParam(name = "precio", required = false) int precio,
			@RequestParam(name = "esDestacado", required = true) Boolean esDestacado) {

//		String mailAdmin = (String) request.getSession().getAttribute("TENANT_ADMIN");
//		if (mailAdmin == null) {
//			return new ResponseEntity<Long>(HttpStatus.FORBIDDEN);
//		}
		ContenidoDTO cont = this.getContenidoOmdbById(omdbId);
		cont.setEsPago(false);
		path = path.replace("%26token", "&token");
		path = path.replace("/o/videos/", "/o/videos%2F");
		Long id = null;
		
		if (esSerie) {
			id = contenidoService.altaSerie(mapper.map(cont, Serie.class), proveedorContenidoId, esDestacado);
		} else {
			id = contenidoService.altaPelicula(mapper.map(cont, Pelicula.class), proveedorContenidoId, path,
					esDestacado);
		}
		
		if (esPago) {
			contenidoService.agregarPrecio(id, precio);
		}
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/episodio", method = RequestMethod.POST)
	public ResponseEntity<Long> altaEpisodio(HttpServletRequest request,
			@RequestParam(name = "idSerie", required = true) Long idSerie,
			@RequestParam(name = "path", required = true) String path,
			@RequestParam(name = "episodio", required = false) int episodio,
			@RequestParam(name = "temporada", required = false) int temporada) {

		path = path.replace("%26token", "&token");
		path = path.replace("/o/videos/", "/o/videos%2F");
		Long id = null;
		id = contenidoService.altaEpisodio(idSerie, path, episodio, temporada);

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

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarPorGenero", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> listarPorGenero(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "generoId", required = true) Long generoId) {
		
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Page<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		
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

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarPorActor", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> listarPorActor(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "actorId", required = true) Long actorId) {
		
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Page<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		
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

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarPorDirector", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> listarPorDirector(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "directorId", required = true) Long directorId) {
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Page<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
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

	// crear una api que reciba el contenido que está viendo un usuario
	// junto al tiempo de reproducción
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/guardarReproduccion", method = RequestMethod.GET)
	public ResponseEntity<?> guardarReproduccion(HttpServletRequest request,
			@RequestParam(name = "idUsuario", required = true) Long idUsuario,
			@RequestParam(name = "idContenido", required = true) Long idContenido,
			@RequestParam(name = "Tiempo", required = true) Long tiempo) {
//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		if (tiempo != 0)
			contenidoService.guardarReproduccion(idUsuario, idContenido, tiempo);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/favorito", method = RequestMethod.POST)
	public ResponseEntity<?> marcarFavorito(HttpServletRequest request,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId,
			@RequestParam(name = "usuarioId", required = true) Long usuarioId,
			@RequestParam(name = "esFavorito", required = true) Boolean esFavorito) {

//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		contenidoService.marcarFavorito(contenidoId, esFavorito, usuarioId);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	@PostConstruct
	private void cargarPeliculas() {

		String idPeliculas = "tt0325980,tt1014759,tt0471537,tt0065856,tt0162661,tt0109707,tt0119008,tt1014759,tt3045616,tt1398941,tt1355683,tt2567026,tt0796992,tt1785450,tt3099498,tt2209764,tt1885299,tt1210819,tt1232829,tt1077368,tt0810913,tt0376136,tt1298650,tt1192628,tt1243957,tt1333667,tt1054606,tt0479468,tt0408236,tt0449088,tt0383574,tt0375920,tt0121164,tt0099733,tt0087800,"
				+ "tt0196178,tt0210945,tt0247199,tt0264935,tt0266971,tt0301976,tt0332280,tt1355630,tt0468489,tt0488120,tt0805564,tt1175709,tt1120985,tt0005960,tt0780504,tt1124035,tt2865166,tt1817273,tt1602613,tt3077150,tt2366608,tt1596363,tt3783958,tt2062700,tt0083658,tt7027210,"
				+ "tt0101587,tt0107277,tt0106028,tt0119305,tt0258470,tt0258273,tt2980794,tt0179098,tt0279113,tt0319262,tt0471020,tt0388795,tt0377107,tt0443706,tt0804522,tt0185906,tt0945513,tt2343801,tt1855199,tt1392214,tt2316411,tt2872718,tt1137470,tt2719848,tt1172049,tt4550098,tt5442430,tt3967856,tt3881784,tt0965383,tt4971344,tt2071424,"
				+ "tt2071424,tt0825232,tt0337741,tt0305224,tt0257360,tt0237572,tt0116996,tt0311376,tt0112744,tt6075386,tt0104257,tt0104804,tt0104427,tt0093277,tt0092699,tt0094332,tt0091188,tt0086425,tt0083678,tt0082979,tt0082934,tt0081505,tt0077621,tt0074906,tt0074777,tt0073486,tt0073008,tt0366444,tt0371130,tt0070290,"
				+ "tt5095030,tt5109784,tt5198670,tt1716777,tt1077368,tt1598822,tt1179258,tt1138489,tt0486655,tt0427327,tt0466839,tt0165982,tt0283139,tt0277027,tt1014759,tt0160916,tt0140379,tt0120646,tt0120794,tt0120323,tt0117247,tt0117924,tt0118055,tt0112792,tt0993846,tt0106226,tt0104765,tt0103776,tt0101912,tt0100530,tt0097322,tt0094947,"
				+ "tt1547921,tt1904996,tt1408972,tt0209475,tt0252076,tt0120094,tt3215790,tt0118615,tt0299930,tt0092890,"
				+ "tt0356460,tt1708494,tt0120399,tt0120399,tt0159824,tt0445935,tt0458522,tt0115710";

		List<String> listaPeliculas = Arrays.asList(idPeliculas.split(","));

		for (String peli : listaPeliculas) {
			System.out.println(peli);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ContenidoDTO cont = this.getContenidoOmdbById(peli);
			cont.setEsPago(false);
			contenidoService.altaPelicula(mapper.map(cont, Pelicula.class), new Long(1), "", false);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarTodasPeliculas", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> buscarTodoContenido(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end) {

//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Page<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		
		Pageable pag = PageUtils.getPageRequest(start, end, null, null);
		Page<Pelicula> pelis = contenidoService.buscarTodasLasPeliculas(pag);

		Page<ContenidoDTO> dtoPage = pelis.map(new Converter<Pelicula, ContenidoDTO>() {
			@Override
			public ContenidoDTO convert(Pelicula peli) {
				return mapper.map(peli, ContenidoDTO.class);
			}
		});
		return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarTodasSeries", method = RequestMethod.GET)
	public ResponseEntity<Page<ContenidoDTO>> buscarTodasSeries(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end) {

//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Page<ContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		Pageable pag = PageUtils.getPageRequest(start, end, null, null);
		Page<Serie> series = contenidoService.buscarTodasLasSeries(pag);

		Page<ContenidoDTO> dtoPage = series.map(new Converter<Serie, ContenidoDTO>() {
			@Override
			public ContenidoDTO convert(Serie serie) {
				return mapper.map(serie, ContenidoDTO.class);
			}
		});
		return new ResponseEntity<Page<ContenidoDTO>>(dtoPage, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/relojSistema", method = RequestMethod.GET)
	public ResponseEntity<String> relojSistema(HttpServletRequest request) {

//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
//		}

		String res = "";
		Date fecha = new Date();
		DateFormat formato = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		res = formato.format(fecha);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/comentarContenido", method = RequestMethod.POST)
	public ResponseEntity<?> comentarContenido(HttpServletRequest request,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId,
			@RequestParam(name = "usuarioId", required = true) Long usuarioId,
			@RequestParam(name = "comentario", required = true) String comentario) {

		// String mailUsuario = (String) request.getSession()
		// .getAttribute("USUARIO");
		// if (mailUsuario==null){
		// return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		// }
		//

		contenidoService.comentarContenido(contenidoId, comentario, usuarioId);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/valorarContenido", method = RequestMethod.PUT)
	public ResponseEntity<?> valorarContenido(HttpServletRequest request,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId,
			@RequestParam(name = "usuarioId", required = true) Long usuarioId,
			@RequestParam(name = "puntaje", required = true) int puntaje) {

		// String mailUsuario = (String) request.getSession()
		// .getAttribute("USUARIO");
		// if (mailUsuario==null){
		// return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		// }
		contenidoService.valorarContenido(contenidoId, puntaje, usuarioId);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/comprarContenidoPayPerView/", method = RequestMethod.POST)
	public ResponseEntity<?> comprarEspectaculoPayPerView(HttpServletRequest request,
			@RequestParam(name = "idContenido", required = true) Long idContenido,
			@RequestParam(name = "email", required = false) String emailUsuario) {

//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		if (contenidoService.comprarContenido(idContenido, emailUsuario)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/verificarPagoEspectaculo", method = RequestMethod.GET)
	public ResponseEntity<Boolean> verificarPagoEspectaculoPayPerView(HttpServletRequest request,
			@RequestParam(name = "idContenido", required = true) Long idContenido,
			@RequestParam(name = "email", required = false) String emailUsuario) {

//		String mailUsuario = (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		
		if (contenidoService.verificarPagoContenido(idContenido, emailUsuario)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}

	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/verDatoContenido", method = RequestMethod.GET)
	public ResponseEntity<ContenidoDTO> verDatoContenido(HttpServletRequest request,
			@RequestParam(name = "idContenido", required = true) Long idContenido) {
		ContenidoDTO cont=mapper.map(contenidoService.verDatoContenido(idContenido), ContenidoDTO.class);
		return new ResponseEntity<ContenidoDTO>(cont,HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/esDestacado", method = RequestMethod.PUT)
	public ResponseEntity<?> esDestacado(HttpServletRequest request, 
			@RequestParam(name = "contenidoId", required = true) Long idContenido,
			@RequestParam(name = "destacado", required = true) Boolean esDestacado,
			@RequestParam(name = "email", required = false) String email) {
		
//		String mailAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		
		
		
		if (adminTenantService.esDestacado(idContenido, esDestacado, email)) {
			return new ResponseEntity<Boolean>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
	}

}
