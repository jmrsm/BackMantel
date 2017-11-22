package com.tsijee01.service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.SuperAdmin;

public interface SuperAdminService {

	boolean altaSuperAdmin(SuperAdmin map, String password);
	boolean altaAdmin(AdminTenant adminTenant, Long proveedorContenidoId, String passwordPlano);
	void bloquearUsuario(String email, Boolean habilitado);
	void bloquearContenido(Long idContenido, Boolean bloquear);
}
