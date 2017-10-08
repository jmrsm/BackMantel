package com.tsijee01.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.rest.dto.AdminTenantDTO;
import com.tsijee01.service.AdminTenantService;
import com.tsijee01.util.Password;

@Service
public class AdminTenantServiceBean implements AdminTenantService {

	@Autowired
	private AdminTenantRepository adminRepository;

	@Autowired
	private Password passwordUtil;

	public boolean altaAdminTenant(AdminTenant at) {

		Optional <AdminTenant> old = adminRepository.findByEmail(at.getEmail());
		if (old.isPresent()){
			return false;
		} else {
			at.setPassowd(passwordUtil.hasherPassword(at.getPassowd()));
			adminRepository.save(at);
			return true;
		}
	}
	
	public boolean loginAdminTenant(String email, String password) {

		Optional <AdminTenant> dbAdminTenant = adminRepository.findByEmail(email);
		if (dbAdminTenant.isPresent()) {
			if (passwordUtil.checkearPassword(password, dbAdminTenant.get().getPassowd())){
				// TODO loguear el usuario al sistema 
				return true;
			}
		}
		return false;
		
	}
	@Override
	public String existeUser(String email) {
		// TODO Auto-generated method stub
		if(adminRepository.existsByEmail(email)){
			return "Existe";
		}else{
			return "No existe";
		}
		
	}
	
	

}
