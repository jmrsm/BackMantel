package com.tsijee01.persistence.service.bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.service.AdminTenantService;


@Service
public class AdminTenantServiceBean implements AdminTenantService {

	@Autowired
	private AdminTenantRepository adminRepository;

	public void altaAdminTenant(AdminTenant at) {
		// TODO Auto-generated method stub
		
	}

	
}
