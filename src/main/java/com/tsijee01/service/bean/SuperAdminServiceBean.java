package com.tsijee01.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.repository.SuperAdminRepository;
import com.tsijee01.rest.dto.AdminTenantDTO;
import com.tsijee01.service.SuperAdminService;
import com.tsijee01.util.Password;

@Service
public class SuperAdminServiceBean implements SuperAdminService {

	@Autowired
	private SuperAdminRepository superAdminRepository;
	
	
	
	@Autowired
	private Password passwordUtil;

	public boolean login(String email, String password) {

		Optional<SuperAdmin> sa = superAdminRepository.findOneByEmail(email);
		if (sa.isPresent()) {
			if (passwordUtil.checkearPassword(password, sa.get().getPassowd())){
				// TODO loguear el usuario al sistema 
				return true;
			}
			
		}
		return false;
	}
	@Override
	public String existeUser(String email) {
		// TODO Auto-generated method stub
		if(superAdminRepository.existsByEmail(email)){
			return "Existe";
		}else{
			return "No existe";
		}
			
		
	}

	@Override
	public boolean alta(AdminTenantDTO adminTenant, Long proveedorContenidoId) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
