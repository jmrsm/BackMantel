package com.tsijee01.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.ProveedorContenidoRepository;
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
	private ProveedorContenidoRepository proveedorContenidoRepository;

	@Autowired
	private Password passwordUtil;

	@Override
	public boolean altaAdmin(AdminTenant adminTenant, Long proveedorContenidoId, String passwordPlano) {
		adminTenant.setPassowd(passwordUtil.hasherPassword(passwordPlano));
		if (!adminTenantRepository.findOneByEmail(adminTenant.getEmail()).isPresent()) {
			Optional<ProveedorContenido> pc = proveedorContenidoRepository.findOne(proveedorContenidoId);
			if (pc.isPresent()) {
				adminTenant.setProveedorContenido(pc.get());
				adminTenantRepository.save(adminTenant);
				pc.get().getAdmins().add(adminTenant);
				proveedorContenidoRepository.save(pc.get());
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean altaSuperAdmin(SuperAdmin superAdmin, String password) {

		superAdmin.setPassowd(passwordUtil.hasherPassword(password));
		if (!superAdminRepository.findOneByEmail(superAdmin.getEmail()).isPresent()) {
			superAdminRepository.save(superAdmin);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean altaSuperAdmin() {
		SuperAdmin s=new SuperAdmin();
		s.setApellido("Admin");
		s.setEmail("admin@gmail.com");
		s.setNombre("alita");
		s.setPassowd(passwordUtil.hasherPassword("1234"));
		superAdminRepository.save(s);
		return true;
	}
}
