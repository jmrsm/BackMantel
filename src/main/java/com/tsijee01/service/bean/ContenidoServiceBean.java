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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsijee01.persistence.model.Actor;
import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Director;
import com.tsijee01.persistence.model.EventoDeportivo;
import com.tsijee01.persistence.model.EventoEspectaculo;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.persistence.repository.ActorRepository;
import com.tsijee01.persistence.repository.CategoriaContenidoRepository;
import com.tsijee01.persistence.repository.DirectorRepository;
import com.tsijee01.persistence.repository.EventoDeportivoRepository;
import com.tsijee01.persistence.repository.EventoEspectaculoRepository;
import com.tsijee01.persistence.repository.PeliculRepository;
import com.tsijee01.persistence.repository.ProveedorContenidoRepository;
import com.tsijee01.persistence.repository.SerieRepository;
import com.tsijee01.rest.dto.ContenidoFullDTO;
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
	public boolean altaContenido(ContenidoFullDTO contenido) {

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
	public boolean altaSerie(Serie contenido, int temporada, int capitulo) {
		
		Serie serie = new Serie(contenido);
		serie.setCapitulo(capitulo);
		serie.setTemporada(temporada);
		return true;
		
	}
	
	@Override
	public boolean altaPelicula(Pelicula contenido) {

		Pelicula peli = new Pelicula(contenido);
		peliculaRepositoy.save(peli);
		return true;
	}
}
