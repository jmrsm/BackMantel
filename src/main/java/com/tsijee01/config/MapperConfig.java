package com.tsijee01.config;

import org.springframework.stereotype.Component;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.rest.dto.AdminTenantDTO;

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

	}

	private void configureAdmintenantMapper() {

		this.factory.classMap(AdminTenant.class, AdminTenantDTO.class)
				.customize(new CustomMapper<AdminTenant, AdminTenantDTO>() {
					@Override
					public void mapAtoB(AdminTenant a, AdminTenantDTO b, MappingContext context) {
						// esto es a modo de ejemplo si lo que se va a mapear
						// tiene el mismo nombre en las propiedades se mapea
						// bien solo, aca definimos cuando el mapeo no es directo
						// b.setApellido(a.getApellido());

					}

					@Override
					public void mapBtoA(AdminTenantDTO b, AdminTenant a, MappingContext context) {
						// a.setApellido(b.getApellido());
					}
				}).byDefault().register();
	}

}
