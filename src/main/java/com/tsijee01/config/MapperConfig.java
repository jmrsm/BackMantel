package com.tsijee01.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.EventoDeportivo;
import com.tsijee01.persistence.model.EventoEspectaculo;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.rest.dto.AdminTenantDTO;
import com.tsijee01.rest.dto.ContenidoFullDTO;

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

	private void configureContenidoMapper() {

		this.factory.classMap(ContenidoFullDTO.class, Contenido.class)
				.customize(new CustomMapper<ContenidoFullDTO, Contenido>() {
					@Override
					public void mapAtoB(ContenidoFullDTO a, Contenido b, MappingContext context) {
						b.setDuracion(a.getDuracion()) ;
						b.setId(a.getId());
						b.setFechaPublicado(new Date()); 
						b.setTitulo(a.getTitulo());
						b.setDescipcion(a.getDescipcion());
						// TODO ver como vamos a manejar el ranking
						b.setRanking(0);
						// TODO ver como subimos la foto
						b.setFotoPortada("");
						// TODO ver como subimos el contenido
						b.setPath("");
					}

					@Override
					public void mapBtoA(Contenido b, ContenidoFullDTO a, MappingContext context) {
					}
				}).byDefault().register();
	}
	private void configurePeliculaMapper() {

		this.factory.classMap(ContenidoFullDTO.class, Pelicula.class)
				.customize(new CustomMapper<ContenidoFullDTO, Pelicula>() {
					@Override
					public void mapAtoB(ContenidoFullDTO a, Pelicula b, MappingContext context) {
						super.mapAtoB(a, b, context);
					}

					@Override
					public void mapBtoA(Pelicula b, ContenidoFullDTO a, MappingContext context) {
					}
				}).byDefault().register();
	}
	
	private void configureSerieMapper() {
		this.factory.classMap(ContenidoFullDTO.class, Serie.class)
				.customize(new CustomMapper<ContenidoFullDTO, Serie>() {
					@Override
					public void mapAtoB(ContenidoFullDTO a, Serie b, MappingContext context) {
						super.mapAtoB(a, b, context);
						b.setCapitulo(a.getSerieCapitulo());
						b.setTemporada(a.getSerieTemporada());
					}

					@Override
					public void mapBtoA(Serie b, ContenidoFullDTO a, MappingContext context) {
					}
				}).byDefault().register();
	}
	
	private void configureEventoDeportivoMapper() {
		this.factory.classMap(ContenidoFullDTO.class, EventoDeportivo.class)
				.customize(new CustomMapper<ContenidoFullDTO, EventoDeportivo>() {
					@Override
					public void mapAtoB(ContenidoFullDTO a, EventoDeportivo b, MappingContext context) {
						super.mapAtoB(a, b, context);
						b.setNombreDeporte(a.getEventoDeportivoNombreDeporte());
						b.setNombreEquipoLocal(a.getEventoDeportivoNombreEquipoLocal());
						b.setNombreEquipoVisitante(a.getEventoDeportivoNombreEquipoVisitante());
					}
					@Override
					public void mapBtoA(EventoDeportivo b, ContenidoFullDTO a, MappingContext context) {
					}
				}).byDefault().register();
	}
	
	private void configureEventoEspectaculoMapper() {
		this.factory.classMap(ContenidoFullDTO.class, EventoEspectaculo.class)
				.customize(new CustomMapper<ContenidoFullDTO, EventoEspectaculo>() {
					@Override
					public void mapAtoB(ContenidoFullDTO a, EventoEspectaculo b, MappingContext context) {
						super.mapAtoB(a, b, context);
						b.setFechaInicio(a.getEventoEspectaculoFechaInicio());
					}
					@Override
					public void mapBtoA(EventoEspectaculo b, ContenidoFullDTO a, MappingContext context) {
					}
				}).byDefault().register();
	}
}
