package com.tsijee01.config;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.Comentario;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.EventoDeportivo;
import com.tsijee01.persistence.model.EventoEspectaculo;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.rest.dto.AdminTenantDTO;
import com.tsijee01.rest.dto.ComentarioDTO;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.rest.dto.TipoContenidoEnum;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.unenhance.HibernateUnenhanceStrategy;

@Component
public class MapperConfig extends ConfigurableMapper {

	private MapperFactory factory;

	@Override
	protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
		factoryBuilder.unenhanceStrategy(new HibernateUnenhanceStrategy());
	}

	@Override
	protected void configure(final MapperFactory factory) {
		this.factory = factory;
		this.configureAdmintenantMapper();
		this.configureContenidoMapper();
		this.configurePeliculaMapper();
		this.configureEventoDeportivoMapper();
		this.configureSerieMapper();
		this.configureEventoEspectaculoMapper();
		this.configureComentarioMapper();
	}

	private void configureAdmintenantMapper() {

		this.factory.classMap(AdminTenant.class, AdminTenantDTO.class)
				.customize(new CustomMapper<AdminTenant, AdminTenantDTO>() {
					@Override
					public void mapAtoB(AdminTenant a, AdminTenantDTO b, MappingContext context) {
						// esto es a modo de ejemplo si lo que se va a mapear
						// tiene el mismo nombre en las propiedades se mapea
						// bien solo, aca definimos cuando el mapeo no es directo
						

					}

					@Override
					public void mapBtoA(AdminTenantDTO b, AdminTenant a, MappingContext context) {
						// a.setApellido(b.getApellido());
					}
				}).byDefault().register();
	}

	private void configureComentarioMapper() {

		this.factory.classMap(Comentario.class, ComentarioDTO.class)
				.customize(new CustomMapper<Comentario, ComentarioDTO>() {
					@Override
					public void mapAtoB(Comentario a, ComentarioDTO b, MappingContext context) {
						if (a.getMarcaSpoiler()!=null && a.getMarcaSpoiler().size() > 0) {
							b.setEsSpoiler(true);
						}
					}

					@Override
					public void mapBtoA(ComentarioDTO b, Comentario a, MappingContext context) {
						
					}
				}).byDefault().register();
	}

	
	private void configureContenidoMapper() {

		this.factory.classMap(ContenidoDTO.class, Contenido.class)
				.customize(new CustomMapper<ContenidoDTO, Contenido>() {
					@Override
					public void mapAtoB(ContenidoDTO a, Contenido b, MappingContext context) {
						if (a.getRanking()==null){
							b.setRanking(new BigDecimal(0));
							if (a.getEsPago()==null) {
								b.setEsPago(false);
							}
							else {
								b.setEsPago(a.getEsPago());
							}
						}
						
						
//						b.setDuracion(a.getDuracion()) ;
//						b.setId(a.getId());
//						b.setFechaPublicado(new Date()); 
//						b.setTitulo(a.getTitulo());
//						b.setDescipcion(a.getDescipcion());
//						// TODO ver como vamos a manejar el ranking
//						b.setRanking(new BigDecimal(0));
//						// TODO ver como subimos la foto
//						b.setFotoPortada("");
//						// TODO ver como subimos el contenido
//						b.setPath("");
					}

					@Override
					public void mapBtoA(Contenido b, ContenidoDTO a, MappingContext context) {
					}
				}).byDefault().register();
	}
	private void configurePeliculaMapper() {

		this.factory.classMap(ContenidoDTO.class, Pelicula.class)
				.customize(new CustomMapper<ContenidoDTO, Pelicula>() {
					@Override
					public void mapAtoB(ContenidoDTO a, Pelicula b, MappingContext context) {
						super.mapAtoB(a, b, context);
					}

					@Override
					public void mapBtoA(Pelicula b, ContenidoDTO a, MappingContext context) {
						super.mapBtoA(b, a, context);
						a.setTipoContenido(TipoContenidoEnum.PELICULA);
					}
				}).byDefault().register();
	}
	
	private void configureSerieMapper() {
		this.factory.classMap(ContenidoDTO.class, Serie.class)
				.customize(new CustomMapper<ContenidoDTO, Serie>() {
					@Override
					public void mapAtoB(ContenidoDTO a, Serie b, MappingContext context) {
						super.mapAtoB(a, b, context);
//						b.setCapitulo(a.getSerieCapitulo());
//						b.setTemporada(a.getSerieTemporada());
					}

					@Override
					public void mapBtoA(Serie b, ContenidoDTO a, MappingContext context) {
						super.mapBtoA(b, a, context);
						a.setTipoContenido(TipoContenidoEnum.SERIE);
					}
				}).byDefault().register();
	}
	
	private void configureEventoDeportivoMapper() {
		this.factory.classMap(ContenidoDTO.class, EventoDeportivo.class)
				.customize(new CustomMapper<ContenidoDTO, EventoDeportivo>() {
					@Override
					public void mapAtoB(ContenidoDTO a, EventoDeportivo b, MappingContext context) {
						super.mapAtoB(a, b, context);
						b.setNombreDeporte(a.getEventoDeportivoNombreDeporte());
						b.setNombreEquipoLocal(a.getEventoDeportivoNombreEquipoLocal());
						b.setNombreEquipoVisitante(a.getEventoDeportivoNombreEquipoVisitante());
					}
					@Override
					public void mapBtoA(EventoDeportivo b, ContenidoDTO a, MappingContext context) {
						super.mapBtoA(b, a, context);
						a.setTipoContenido(TipoContenidoEnum.EVENTO_DEPORTIVO);
					}
				}).byDefault().register();
	}
	
	private void configureEventoEspectaculoMapper() {
		this.factory.classMap(ContenidoDTO.class, EventoEspectaculo.class)
				.customize(new CustomMapper<ContenidoDTO, EventoEspectaculo>() {
					@Override
					public void mapAtoB(ContenidoDTO a, EventoEspectaculo b, MappingContext context) {
						super.mapAtoB(a, b, context);
						
					}
					@Override
					public void mapBtoA(EventoEspectaculo b, ContenidoDTO a, MappingContext context) {
						super.mapBtoA(b, a, context);
						a.setTipoContenido(TipoContenidoEnum.EVENTO_ESPECTACULO);
					}
				}).byDefault().register();
	}
	
	private void configureContenidoOmdbMapper() {
		this.factory.classMap(ContenidoDTO.class, Contenido.class)
				.customize(new CustomMapper<ContenidoDTO, Contenido>() {
					@Override
					public void mapAtoB(ContenidoDTO a, Contenido b, MappingContext context) {
						super.mapAtoB(a, b, context);
						
//						b.setActores(actores);
//						b.setCategorias(categorias);
//						b.setComentarios(comentarios);
//						b.setDescipcion(a.getPlot());
//						b.setDirectores(directores);
//						b.setDuracion(duracion);
//						b.setFechaPublicado(fechaPublicado);
//						b.setFotoPortada(fotoPortada);
//						b.setId(id);
//						b.setPath(path);
//						b.setProveedorContenido(proveedorContenido);
//						b.setRanking(ranking);
//						b.setSimilares(similares);
//						b.setTitulo(titulo);
//						
					}
					@Override
					public void mapBtoA(Contenido b, ContenidoDTO a, MappingContext context) {
					}
				}).byDefault().register();
	}
}
