package com.tsijee01.service;

import com.tsijee01.rest.dto.AdminTenantDTO;

public interface SuperAdminService {

	boolean alta(AdminTenantDTO adminTenant, Long proveedorContenidoId);

	boolean login(String email, String password);

}
