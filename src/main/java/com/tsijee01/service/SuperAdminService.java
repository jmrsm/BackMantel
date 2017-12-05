package com.tsijee01.service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.SuperAdmin;

public interface SuperAdminService {

	boolean altaSuperAdmin(SuperAdmin map, String password);
	boolean altaAdmin(AdminTenant adminTenant, Long proveedorContenidoId, String passwordPlano);
	Boolean bloquearUsuario(String email, Boolean habilitado);
	Boolean bloquearContenido(Long idContenido, Boolean bloquear);
}
