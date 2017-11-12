package com.tsijee01.service.bean;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsijee01.persistence.model.Actor;
import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.CapituloSerie;
import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.model.Comentario;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Director;
import com.tsijee01.persistence.model.Evento;
import com.tsijee01.persistence.model.EventoDeportivo;
import com.tsijee01.persistence.model.EventoEspectaculo;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.persistence.model.TemporadaSerie;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.ActorRepository;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.CapituloRepository;
import com.tsijee01.persistence.repository.CategoriaContenidoRepository;
import com.tsijee01.persistence.repository.ComentarioRepository;
import com.tsijee01.persistence.repository.ContenidoRepository;
import com.tsijee01.persistence.repository.DirectorRepository;
import com.tsijee01.persistence.repository.EventoDeportivoRepository;
import com.tsijee01.persistence.repository.EventoEspectaculoRepository;
import com.tsijee01.persistence.repository.EventoRepository;
import com.tsijee01.persistence.repository.HistorialContenidoRepository;
import com.tsijee01.persistence.repository.PeliculRepository;
import com.tsijee01.persistence.repository.ProveedorContenidoRepository;
import com.tsijee01.persistence.repository.SerieRepository;
import com.tsijee01.persistence.repository.TemporadaSerieRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.rest.dto.TipoContenidoEnum;
import com.tsijee01.service.ContenidoService;

import ma.glasnost.orika.MapperFacade;

@Service
public class ContenidoServiceBean implements ContenidoService {

	@Autowired
	ContenidoRepository contenidoRepository;

	@Autowired
	ComentarioRepository comentarioRepository;

	@Autowired
	CapituloRepository capituloRepository;

	@Autowired
	PeliculRepository peliculaRepositoy;

	@Autowired
	SerieRepository serieRepositoy;

	@Autowired
	EventoEspectaculoRepository eventoEspectaculoRepositoy;

	@Autowired
	EventoDeportivoRepository eventoDeportivoRepository;

	@Autowired
	ProveedorContenidoRepository proveedorContenidoRepository;

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	DirectorRepository directorRepository;

	@Autowired
	CategoriaContenidoRepository categoriaRepository;

	@Autowired
	SerieRepository serieRepository;

	@Autowired
	HistorialContenidoRepository historialContenidoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AdminTenantRepository adminTenantRepository;

	@Autowired
	TemporadaSerieRepository temporadaSerieRepository;

	@Autowired
	EventoRepository eventoRepository;

	@Autowired
	MapperFacade mapper;

	@Value("${contenidoPath}")
	private String contenidoPath;

	public void altaContenidoMultimedia(Long contenidoId, MultipartFile contenido) throws IOException {

		// TODO crear una estructura de carpetas con el proveedor de contenido y
		// el tipo de contenido si es serie carpeta para las temporadas
		String pathContenido = contenidoPath + "\\" + String.valueOf(contenidoId) + "\\";

		byte[] bytes = contenido.getBytes();
		File espectaculoPath = new File(pathContenido);
		if (!espectaculoPath.exists()) {
			espectaculoPath.mkdirs();
		}
		Path path = Paths.get(pathContenido + contenido.getOriginalFilename());
		Files.write(path, bytes);
	}

	@Override
	public boolean altaContenido(ContenidoDTO contenido) {

		TipoContenidoEnum tcEnum = contenido.getTipoContenido();

		if (tcEnum.equals(TipoContenidoEnum.EVENTO_DEPORTIVO)) {
			EventoDeportivo eventoDeportivo = mapper.map(contenido, EventoDeportivo.class);
			eventoDeportivo = (EventoDeportivo) this.crearContenido(eventoDeportivo);
			if (contenido.getEsPago()) {
				eventoDeportivo.setEsPago(true);
				eventoDeportivo.setPrecio(contenido.getPrecio());
				eventoDeportivo.setFechaInicio(contenido.getFechaInicio());
			}
			eventoDeportivo.setNombreEquipoLocal(contenido.getEventoDeportivoNombreEquipoLocal());
			eventoDeportivo.setNombreEquipoVisitante(contenido.getEventoDeportivoNombreEquipoVisitante());
			eventoDeportivo.setNombreDeporte(contenido.getEventoDeportivoNombreDeporte());
			eventoDeportivoRepository.save(eventoDeportivo);
		} else if (tcEnum.equals(TipoContenidoEnum.EVENTO_ESPECTACULO)) {
			EventoEspectaculo espectaculo = mapper.map(contenido, EventoEspectaculo.class);

			espectaculo = (EventoEspectaculo) this.crearContenido(espectaculo);
			if (contenido.getEsPago()) {
				espectaculo.setEsPago(true);
				espectaculo.setPrecio(contenido.getPrecio());
				espectaculo.setFechaInicio(contenido.getFechaInicio());
			}

			eventoEspectaculoRepositoy.save(espectaculo);
		} else if (tcEnum.equals(TipoContenidoEnum.PELICULA)) {
			Pelicula peli = mapper.map(contenido, Pelicula.class);
			peli = (Pelicula) this.crearContenido(peli);
			peliculaRepositoy.save(peli);
		} else if (tcEnum.equals(TipoContenidoEnum.SERIE)) {
			Serie serie = mapper.map(contenido, Serie.class);
			serie = (Serie) this.crearContenido(serie);
			serieRepository.save(serie);
		}
		return true;
	}

	// lo que sean objectos se los trae de la base y agrega al objeto que
	// estamos creando los atributos simples son mapeados en el mapper
	private Contenido crearContenido(Contenido cont) {

		Optional<ProveedorContenido> pc = proveedorContenidoRepository.findOne(cont.getProveedorContenido().getId());
		cont.setProveedorContenido(pc.get());
		List<Actor> actores = new ArrayList<Actor>();
		if (cont.getActores() !=null){
			cont.getActores().forEach(act -> {
				Actor a = new Actor();
				a.setId(act.getId());
				actores.add(a);
			});
			actores.forEach(act -> {
				cont.getActores().remove(act);
				Actor actorDB = actorRepository.findOne(act.getId()).get();
				actorDB.getContenidos().add(cont);
				cont.getActores().add(actorDB);
			});
		}
		
		if (cont.getCategorias() != null){
			List<Categoria> categorias = new ArrayList<Categoria>();
			cont.getCategorias().forEach(cat -> {
				Categoria c = new Categoria();
				c.setId(cat.getId());
				categorias.add(c);
			});
			categorias.forEach(cat -> {
				cont.getCategorias().remove(cat);
				Categoria categoriaDB = categoriaRepository.findOne(cat.getId()).get();
				categoriaDB.getContenidoCategoria().add(cont);
				cont.getCategorias().add(categoriaDB);
			});

		}
		if (cont.getDirectores()!=null){
		List<Director> directores = new ArrayList<Director>();
		cont.getDirectores().forEach(dir -> {
			Director d = new Director();
			d.setId(dir.getId());
			directores.add(d);
		});
		directores.forEach(dir -> {
			cont.getDirectores().remove(dir);
			Director direcotrDB = directorRepository.findOne(dir.getId()).get();
			direcotrDB.getContenido().add(cont);
			cont.getDirectores().add(direcotrDB);
		});
		}
		

		return cont;
	}

	@Override
	public Long altaSerie(Serie serie, Long proveedorContenidoId, Boolean esDestacado) {

		this.guardarContenido(serie, proveedorContenidoId, esDestacado);
		serie.setTemporadas(new ArrayList<TemporadaSerie>());
		return serieRepository.save(serie).getId();

	}

	@Override
	public Long altaPelicula(Pelicula peli, Long proveedorContenidoId, String path, Boolean esDestacado) {

		this.guardarContenido(peli, proveedorContenidoId, esDestacado);
		peli.setPath(path);
		return peliculaRepositoy.save(peli).getId();

	}

	private void guardarContenido(Contenido cont, Long proveedorContenidoId, Boolean esDestacado) {

		cont.setPath("");
		cont.getActores().forEach(ac -> {
			Optional<Actor> oa = actorRepository.findByNombreCompleto(ac.getNombreCompleto());
			ac.setId(oa.isPresent() ? oa.get().getId() : actorRepository.save(ac).getId());

		});
		cont.getCategorias().forEach(ca -> {
			Optional<Categoria> oc = categoriaRepository.findByNombreCategoria(ca.getNombreCategoria());
			ca.setId(oc.isPresent() ? oc.get().getId() : categoriaRepository.save(ca).getId());
		});
		cont.getDirectores().forEach(dir -> {
			Optional<Director> od = directorRepository.findByNombreCompleto(dir.getNombreCompleto());
			dir.setId(od.isPresent() ? od.get().getId() : directorRepository.save(dir).getId());
		});
		ProveedorContenido pc = proveedorContenidoRepository.findOne(proveedorContenidoId).get();

		cont.setProveedorContenido(pc);
		cont.setEsDestacado(esDestacado);

	}

	@Override
	public Page<Pelicula> buscarPelicula(Pageable pag, String query) {
		return peliculaRepositoy.findByTituloContaining(pag, query);

	}

	@Override
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	public Page<Pelicula> buscarPeliculaPorGenero(Pageable pag, Long generoId) {
		Optional<Categoria> c = categoriaRepository.findOne(generoId);
		Page<Pelicula> listaPorGenero = peliculaRepositoy.findByCategorias(pag, c.get());
		return listaPorGenero;
	}

	@Override
	public Page<Pelicula> buscarPeliculaPorActor(Pageable pag, Long actorId) {
		Optional<Actor> actor = actorRepository.findOne(actorId);
		Page<Pelicula> listaPorActor = peliculaRepositoy.findByActores(pag, actor.get());
		return listaPorActor;
	}

	@Override
	public Page<Pelicula> buscarPeliculaPorDirector(Pageable pag, Long directorId) {
		Optional<Director> dire = directorRepository.findOne(directorId);
		Page<Pelicula> listaPorDirector = peliculaRepositoy.findByDirectores(pag, dire.get());
		return listaPorDirector;
	}

	@Override
	public List<Categoria> obtenerTiposContenido() {

		return categoriaRepository.findAll();
	}

	public List<Pelicula> recomendarAUsuario(Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findOne(usuarioId);
		List<HistorialContenido> contenido = historialContenidoRepository.findByUsuario(usuario.get());
		return null;
	}

	@Override
	public void marcarDestacado(Long contenidoId, Boolean esDestacado) {
		Optional<Contenido> cont = contenidoRepository.findOne(contenidoId);
		cont.get().setEsDestacado(esDestacado);
		contenidoRepository.save(cont.get());

	}

	@Override
	public List<Contenido> obtenermicontenido(String email) {
		Optional<AdminTenant> admin = adminTenantRepository.findOneByEmail(email);
		List<Contenido> contenido = contenidoRepository.findByProveedorContenido(admin.get().getId());
		return contenido;
	}

	@Override
	public void guardarReproduccion(Long idUsuario, Long idContenido, Long tiempo) {

		Optional<Usuario> u = usuarioRepository.findOne(idUsuario);
		Optional<Contenido> cont = contenidoRepository.findOne(idContenido);
		Optional<HistorialContenido> h = historialContenidoRepository.findByContenidoAndUsuario(cont.get(), u.get());

		if (h.isPresent() && h.get().isVisto()) {
			h.get().setTiempoDeReproduccion(tiempo);
			historialContenidoRepository.save(h.get());
		} else {
			HistorialContenido hn = new HistorialContenido();
			hn.setFechaReproduccion(new Date());
			hn.setContenido(cont.get());
			hn.setUsuario(u.get());
			hn.setTiempoDeReproduccion(tiempo);
			hn.setFavorito(false);
			hn.setVisto(true);
			historialContenidoRepository.save(hn);
		}

	}

	@Override

	public void marcarFavorito(Long contenidoId, Boolean esFavorito, Long usuarioId) {
		Optional<Contenido> cont = contenidoRepository.findOne(contenidoId);
		Optional<Usuario> u = usuarioRepository.findOne(usuarioId);
		Optional<HistorialContenido> h = historialContenidoRepository.findByContenidoAndUsuario(cont.get(), u.get());
		if (!h.isPresent()) {
			HistorialContenido hn = new HistorialContenido();
			hn.setContenido(cont.get());
			hn.setPuntuacion(0);
			hn.setUsuario(u.get());
			hn.setVisto(false);
			hn.setFavorito(esFavorito);
			historialContenidoRepository.save(hn);
		} else {
			h.get().setFavorito(esFavorito);
			historialContenidoRepository.save(h.get());
		}
	}

	public List<ContenidoDTO> listarContenidoProveedor(String email) {

		List<ContenidoDTO> ret = new ArrayList<>();
		AdminTenant at = adminTenantRepository.findOneByEmail(email).get();
		ProveedorContenido pc = at.getProveedorContenido();

		ret.addAll(mapper.mapAsList(peliculaRepositoy.findByProveedorContenido(pc), ContenidoDTO.class));
		ret.addAll(mapper.mapAsList(serieRepository.findByProveedorContenido(pc), ContenidoDTO.class));
		ret.addAll(mapper.mapAsList(eventoDeportivoRepository.findByProveedorContenido(pc), ContenidoDTO.class));
		ret.addAll(mapper.mapAsList(eventoEspectaculoRepositoy.findByProveedorContenido(pc), ContenidoDTO.class));
		return ret;
	}

	@Override
	public Long verContenido(Long usuarioId, Long contenidoId) {

		Optional<Contenido> cont = contenidoRepository.findOne(contenidoId);
		Optional<Usuario> u = usuarioRepository.findOne(usuarioId);
		Optional<HistorialContenido> h = historialContenidoRepository.findByContenidoAndUsuario(cont.get(), u.get());
		if (h.isPresent()) {
			return h.get().getTiempoDeReproduccion();
		} else {
			HistorialContenido hc = new HistorialContenido();
			hc.setContenido(cont.get());
			hc.setFavorito(false);
			hc.setFechaReproduccion(new Date());
			return new Long(0);
		}
	}

	@Override
	public Page<Pelicula> buscarTodasLasPeliculas(Pageable pag) {
		return peliculaRepositoy.findByEsBloqueadoFalse(pag);
	}
	
	@Override
	public Page<Serie> buscarTodasLasSeries(Pageable pag) {
		return serieRepositoy.findByEsBloqueadoFalse(pag);
	}

	@Override
	public Long altaEpisodio(Long idSerie, String path, int episodio, int temporada) {

		Long idTemp = null;
		Optional<Serie> s = serieRepository.findOne(idSerie);
		Optional<TemporadaSerie> t = temporadaSerieRepository.findByTemporadaAndSerie(temporada, s.get());

		if (!t.isPresent()) {
			TemporadaSerie tp = new TemporadaSerie();
			tp.setTemporada(temporada);
			tp.setCapitulos(new ArrayList<CapituloSerie>());
			tp.setSerie(s.get());
			idTemp = temporadaSerieRepository.save(tp).getId();
		} else {
			idTemp = t.get().getId();
		}

		CapituloSerie c = new CapituloSerie();
		c.setCapitulo(episodio);
		c.setPath(path);

		c.setTemporada(temporadaSerieRepository.findOne(idTemp).get());

		return capituloRepository.save(c).getId();
	}

	@Override
	public void comentarContenido(Long contenidoId, String comentario, Long usuarioId) {

		Optional<Contenido> cont = contenidoRepository.findOne(contenidoId);
		Comentario coment = new Comentario();
		Optional<Usuario> u = usuarioRepository.findOne(usuarioId);

		if (cont.isPresent()) {
			coment.setContenidoComentario(comentario);
			coment.setContenido(cont.get());
			coment.setUsuario(u.get());
		} else {

		}

		comentarioRepository.save(coment).getId();
	}

	@Override
	public void marcarSpoiler(Long comentarioId, Long usuarioId) {

		Optional<Comentario> c = comentarioRepository.findOne(comentarioId);
		if (c.isPresent()) {
			Optional<Usuario> u = usuarioRepository.findOne(usuarioId);
			c.get().getMarcaSpoiler().add(u.get());
			comentarioRepository.save(c.get());
		}
	}

	@Override
	public void valorarContenido(Long contenidoId, int puntaje, Long usuarioId) {

		Optional<Usuario> u = usuarioRepository.findOne(usuarioId);
		Optional<Contenido> cont = contenidoRepository.findOne(contenidoId);

		Optional<HistorialContenido> hc = historialContenidoRepository.findByUsuarioAndContenido(u.get(), cont.get());
		if (!hc.isPresent()) {
			hc = historialContenidoRepository.findByUsuarioAndContenido(u.get(), cont.get());
			hc.get().setContenido(cont.get());
			hc.get().setUsuario(u.get());
			hc.get().setVisto(false);
			hc.get().setFavorito(false);

		} else {
			if (hc.get().getPuntuacion() != 0) {
				return;
			}
		}

		hc.get().setPuntuacion(puntaje);

		BigDecimal cantVotos = new BigDecimal(cont.get().getCantVotos());
		BigDecimal punt = new BigDecimal(puntaje);

		BigDecimal ranking = cont.get().getRanking().multiply(cantVotos);
		cont.get().setCantVotos(cont.get().getCantVotos() + 1);
		ranking = ranking.add(punt);

		BigDecimal uno = new BigDecimal(1);
		cantVotos = cantVotos.add(uno);
		ranking = ranking.divide(cantVotos, 1, BigDecimal.ROUND_HALF_UP);
		cont.get().setRanking(ranking);

		contenidoRepository.save(cont.get());
		historialContenidoRepository.save(hc.get());
	}

	@Override
	public boolean comprarEspectaculo(Long idContenido, String emailUsuario) {

		Optional<Evento> evento = eventoRepository.findOne(idContenido);

		if (!evento.isPresent() || !evento.get().getEsPago()) {
			return false;
		} else {
			Optional<Usuario> u = usuarioRepository.findByEmail(emailUsuario);
			if (!u.isPresent()) {
				return false;
			} else {
				Optional<HistorialContenido> hc = historialContenidoRepository.findByContenidoAndUsuario(evento.get(),
						u.get());
				if (hc.isPresent()) {
					hc.get().setPagado(true);
					historialContenidoRepository.save(hc.get());
				} else {
					HistorialContenido h = new HistorialContenido();
					h.setContenido(evento.get());
					h.setUsuario(u.get());
					h.setPagado(true);
					historialContenidoRepository.save(h);
				}
			}
		}
		return true;
	}

	@Override
	public boolean verificarPagoEspectaculo(Long idContenido, String emailUsuario) {
		Optional<Evento> evento = eventoRepository.findOne(idContenido);

		if (!evento.isPresent() || !evento.get().getEsPago()) {
			return false;
		} else {
			Optional<Usuario> u = usuarioRepository.findByEmail(emailUsuario);
			if (!u.isPresent()) {
				return false;
			} else {
				Optional<HistorialContenido> hc = historialContenidoRepository.findByContenidoAndUsuario(evento.get(),
						u.get());
				if (hc.isPresent()) {
					return true;
				} else {
					return false;
				}
			}
		}

	}

}
