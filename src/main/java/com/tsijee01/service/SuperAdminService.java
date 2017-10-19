package com.tsijee01.service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.rest.dto.AdminTenantDTO;

public interface SuperAdminService {

	boolean altaSuperAdmin(SuperAdmin map, String password);
	boolean altaSuperAdmin();
	boolean altaAdmin(AdminTenant adminTenant, Long proveedorContenidoId, String passwordPlano);
}
