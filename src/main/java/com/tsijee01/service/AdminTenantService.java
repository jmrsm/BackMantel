package com.tsijee01.service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.rest.dto.AdminTenantDTO;

public interface AdminTenantService {

	boolean alta(AdminTenantDTO adminTenant, Long proveedorContenidoId);

}
