package com.tsijee01.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.model.TipoUsuarioEnum;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.SuperAdminRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.LoginService.LoginService;
import com.tsijee01.util.Password;

@Service
public class LoginServiceBean implements LoginService {

	@Autowired
	private AdminTenantRepository adminRepository;

	@Autowired
	private SuperAdminRepository superAdminRepository;

	@Autowired
	private Password passwordUtil;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<TipoUsuarioEnum> login(String email, String password) {

		Optional<AdminTenant> at = adminRepository.findOneByEmail(email);
		if (at.isPresent()) {
			if (passwordUtil.checkearPassword(password, at.get().getPassowd())) {
				return Optional.of(TipoUsuarioEnum.TENANT_ADMIN);
			} else {
				return Optional.of(TipoUsuarioEnum.Forbbiden);
			}
		}

		Optional<SuperAdmin> sa = superAdminRepository.findOneByEmail(email);
		if (sa.isPresent()) {
			if (passwordUtil.checkearPassword(password, sa.get().getPassowd())) {
				return Optional.of(TipoUsuarioEnum.SUPER_ADMIN);
			} else {
				return Optional.of(TipoUsuarioEnum.Forbbiden);
			}
		}

		Optional<Usuario> u = usuarioRepository.findOneByEmail(email);
		if (u.isPresent()) {
			if (passwordUtil.checkearPassword(password, u.get().getPassowd())) {
				return Optional.of(TipoUsuarioEnum.Usuario);
			} else {
				return Optional.of(TipoUsuarioEnum.Forbbiden);
			}
		}
		return Optional.empty();
	}

	@Override
	public Long obtenerId(String email) {

		Long id = null;
		Optional<AdminTenant> at = adminRepository.findOneByEmail(email);
		if (at.isPresent()) {
			id = at.get().getId();
		}

		Optional<SuperAdmin> sa = superAdminRepository.findOneByEmail(email);
		if (sa.isPresent()) {
			id = sa.get().getId();
		}

		Optional<Usuario> u = usuarioRepository.findOneByEmail(email);
		if (u.isPresent()) {
			id = u.get().getId();
		}
		return id;

	}

}
