package com.tsijee01.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.SuperAdminRepository;
import com.tsijee01.service.SuperAdminService;
import com.tsijee01.util.Password;

@Service
public class SuperAdminServiceBean implements SuperAdminService {

	@Autowired
	private SuperAdminRepository superAdminRepository;
	
	@Autowired
	private AdminTenantRepository adminTenantRepository;
	
	
	
	@Autowired
	private Password passwordUtil;

	@Override
	public boolean altaAdmin(AdminTenant adminTenant, Long proveedorContenidoId, String passwordPlano) {
		adminTenant.setPassowd(passwordUtil.hasherPassword(passwordPlano));
		if (!adminTenantRepository.findOneByEmail(adminTenant.getEmail()).isPresent()){
			adminTenantRepository.save(adminTenant);
			return true;
		}else {
			return false;
		}
	}
	
	
	@Override
	public boolean altaSuperAdmin(SuperAdmin superAdmin, String password) {
		
		superAdmin.setPassowd(passwordUtil.hasherPassword(password));
		if (!superAdminRepository.findOneByEmail(superAdmin.getEmail()).isPresent()){
			superAdminRepository.save(superAdmin);
			return true;
		}else {
			return false;
		}
		
	}
	
}
