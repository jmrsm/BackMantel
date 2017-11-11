package com.tsijee01.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.ContenidoRepository;
import com.tsijee01.persistence.repository.ProveedorContenidoRepository;
import com.tsijee01.persistence.repository.SuperAdminRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.SuperAdminService;
import com.tsijee01.util.Password;

@Service
public class SuperAdminServiceBean implements SuperAdminService {

	@Autowired
	private SuperAdminRepository superAdminRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ContenidoRepository contenidoRepository;

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
	public void bloquearUsuario(Long idUsuario, Boolean habilitado) {
		
		Optional <Usuario> u = usuarioRepository.findOne(idUsuario);
		
		if (u.isPresent()){
			u.get().setHabilitado(!habilitado);
		}
		else {
		return;
		}
		
		usuarioRepository.save(u.get());
	}

	@Override
	public void bloquearContenido(Long idContenido, Boolean bloquear) {
		
		Optional <Contenido> cont = contenidoRepository.findOne(idContenido);
		
		if (cont.isPresent()){
			cont.get().setEsBloqueado(bloquear);
		}
		else {
		return;
		}
		
		contenidoRepository.save(cont.get());
		
	}

}
