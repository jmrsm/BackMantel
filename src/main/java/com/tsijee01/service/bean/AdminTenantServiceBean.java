package com.tsijee01.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.ContenidoRepository;
import com.tsijee01.rest.dto.AdminTenantDTO;
import com.tsijee01.service.AdminTenantService;
import com.tsijee01.util.Password;

@Service
public class AdminTenantServiceBean implements AdminTenantService {

	@Autowired
	private AdminTenantRepository adminRepository;
	
	@Autowired
	private ContenidoRepository contenidoRepository;

	@Autowired
	private Password passwordUtil;

	public boolean altaAdminTenant(AdminTenant at) {

		Optional <AdminTenant> old = adminRepository.findOneByEmail(at.getEmail());
		if (old.isPresent()){
			return false;
		} else {
			at.setPassowd(passwordUtil.hasherPassword(at.getPassowd()));
			adminRepository.save(at);
			return true;
		}
	}
	
	public boolean loginAdminTenant(String email, String password) {

		Optional <AdminTenant> dbAdminTenant = adminRepository.findOneByEmail(email);
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
	@Override
	public String idUser(String email) {
		
		return ""+adminRepository.findOneByEmail(email).get().getId();
		
	}

	@Override
	public AdminTenant ObtenerAdminTenat(String email) {
		return this.adminRepository.findOneByEmail(email).get();
	}

	@Override
	public boolean esDestacado(Long idContenido, Boolean esDestacado, String email) {
		
		Optional <AdminTenant> admin= adminRepository.findOneByEmail(email);
		Optional <Contenido> c = contenidoRepository.findOne(idContenido);
		Long idProv = c.get().getProveedorContenido().getId();
		
		if (admin.isPresent() && idProv == admin.get().getProveedorContenido().getId()) {
			c.get().setEsDestacado(esDestacado);
			contenidoRepository.save(c.get());
			return true;
		}
		else {
			return false;
		}
	}
	

}
