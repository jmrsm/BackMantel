package com.tsijee01.service;

import com.tsijee01.persistence.model.AdminTenant;

public interface AdminTenantService {

	boolean altaAdminTenant(AdminTenant at);
	
	AdminTenant ObtenerAdminTenat(String email);
	
	String existeUser(String email);

	String idUser(String email);
}
