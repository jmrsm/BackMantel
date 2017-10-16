package com.tsijee01.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Actor;
import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.model.EventoDeportivo;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.repository.ActorRepository;
import com.tsijee01.persistence.repository.CategoriaContenidoRepository;
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
	EventoDeportivoRepository eventoDeporstivoRepository;

	@Autowired
	ProveedorContenidoRepository proveedorContenidoRepository;

	@Autowired
	ActorRepository actorRepository;
	
	@Autowired
	CategoriaContenidoRepository categoriaRepository;

	@Autowired
	MapperFacade mapper;

	@Override
	public boolean altaContenido(ContenidoFullDTO contenido) {
		
		TipoContenidoEnum tcEnum = contenido.getTipoContenido();
		
		if (tcEnum.equals(TipoContenidoEnum.EVENTO_DEPORTIVO)){
			EventoDeportivo ed = new EventoDeportivo(mapper.map(contenido, EventoDeportivo.class));
			;
		}else if (tcEnum.equals(TipoContenidoEnum.EVENTO_ESPECTACULO)){
//			EventoEspectaculo ee = new EventoEspectaculo();
//			mapper.map(contenido, EventoEspectaculo.class);
		}else if (tcEnum.equals(TipoContenidoEnum.PELICULA)){
			
			Pelicula peli = mapper.map(contenido, Pelicula.class);
			
			Optional <ProveedorContenido> pc = proveedorContenidoRepository.findOne(peli.getProveedorContenido().getId());
			peli.setProveedorContenido(pc.get());
			
			List<Actor> actores = new ArrayList<Actor>();
			peli.getActores().forEach(act->{
				Actor a = new Actor();
				a.setId(act.getId());
				actores.add(a);
			});
			actores.forEach(act ->{
				peli.getActores().remove(act);
				Actor actorDB = actorRepository.findOne(act.getId()).get();
				actorDB.getContenidos().add(peli);
				peli.getActores().add(actorDB);
			});
			
			List<Categoria> categorias = new ArrayList<Categoria>();
			peli.getCategorias().forEach(cat->{
				Categoria c = new Categoria();
				c.setId(cat.getId());
				categorias.add(c);
			});
			categorias.forEach(cat ->{
				peli.getCategorias().remove(cat);
				Categoria categoriaDB = categoriaRepository.findOne(cat.getId()).get();
				categoriaDB.getContenidoCategoria().add(peli);
				peli.getCategorias().add(categoriaDB);
			});

			peli.setDirectores(null);
			peliculaRepositoy.save(peli);
			
		}else if (tcEnum.equals(TipoContenidoEnum.SERIE)){
		}
//		// TODO Auto-generated method stub
		return false;
	}

}
