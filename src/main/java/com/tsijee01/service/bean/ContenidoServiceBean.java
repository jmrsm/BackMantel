package com.tsijee01.service.bean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsijee01.persistence.model.Actor;
import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Director;
import com.tsijee01.persistence.model.EventoDeportivo;
import com.tsijee01.persistence.model.EventoEspectaculo;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.persistence.model.TemporadaSerie;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.ActorRepository;
import com.tsijee01.persistence.repository.CategoriaContenidoRepository;
import com.tsijee01.persistence.repository.DirectorRepository;
import com.tsijee01.persistence.repository.EventoDeportivoRepository;
import com.tsijee01.persistence.repository.EventoEspectaculoRepository;
import com.tsijee01.persistence.repository.HistorialContenidoRepository;
import com.tsijee01.persistence.repository.PeliculRepository;
import com.tsijee01.persistence.repository.ProveedorContenidoRepository;
import com.tsijee01.persistence.repository.SerieRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.rest.dto.TipoContenidoEnum;
import com.tsijee01.service.ContenidoService;

import ma.glasnost.orika.MapperFacade;

@Service
public class ContenidoServiceBean implements ContenidoService {

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
			eventoDeportivoRepository.save(eventoDeportivo);
		} else if (tcEnum.equals(TipoContenidoEnum.EVENTO_ESPECTACULO)) {
			EventoEspectaculo espectaculo = mapper.map(contenido, EventoEspectaculo.class);
			espectaculo = (EventoEspectaculo) this.crearContenido(espectaculo);
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

		return cont;
	}

	@Override
	public Long altaSerie(Serie serie, Long proveedorContenidoId) {

		this.guardarContenido(serie, proveedorContenidoId);
		serie.setTemporadas(new ArrayList<TemporadaSerie>());
		return serieRepository.save(serie).getId();
		

	}

	@Override
	public Long altaPelicula(Pelicula peli, Long proveedorContenidoId) {

		this.guardarContenido(peli, proveedorContenidoId);
		return peliculaRepositoy.save(peli).getId();
		
	}

	private void guardarContenido(Contenido cont, Long proveedorContenidoId) {

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
		cont.setProveedorContenido(proveedorContenidoRepository.findOne(proveedorContenidoId).get());
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
	
	public List<Pelicula> recomendarAUsuario(Long usuarioId){
		Optional <Usuario> usuario = usuarioRepository.findOne(usuarioId);
		List<HistorialContenido> contenido = historialContenidoRepository.findByUsuario(usuario.get());
		return null;
	}

}
