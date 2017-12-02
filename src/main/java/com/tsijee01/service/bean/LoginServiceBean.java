package com.tsijee01.service.bean;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.model.TipoUsuarioEnum;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.SuperAdminRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.UsuarioService;
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

	@Autowired
	private UsuarioService usuarioService;

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
			if (passwordUtil.checkearPassword(password, u.get().getPassowd()) && u.get().isHabilitado()) {
				if (u.get().getAgreementId() == null) {
					return Optional.of(TipoUsuarioEnum.NO_PAGO);
				} else if (this.estaAlDia(u.get().getAgreementId())) {
					return Optional.of(TipoUsuarioEnum.USUARIO);
				} else {
					return Optional.of(TipoUsuarioEnum.NO_PAGO);
				}
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

	@Override
	public Optional<Usuario> altaOLoginConGmail(String id, String email, String nombre, String apellido) {
		Optional<Usuario> usr = usuarioRepository.findByEmail(email);
		if (!usr.isPresent()) {
			usuarioService.crearUser(email, null, nombre, apellido, id);
			return usuarioRepository.findByEmail(email);
		}
		if (usr.get().getGmailToken() == null) {
			usr.get().setGmailToken(passwordUtil.hasherPassword(id));
			usuarioRepository.save(usr.get());
		} else if (passwordUtil.checkearPassword(id, usr.get().getGmailToken())) {
			return usr;
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	private boolean estaAlDia(String aggrementId) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization",
				"Basic QVpMZDU5RUVEQ1NBS0IwWEVFRngwRWVkWW9OT0pyTlJiM2FuRkhkcGl1eU1jSmRZWHltREUyR1BtOUM2TzAxeEotdnFPclQzckVTN3BGQVQ6RUhkeDlDTlF2eVpzRDEzdk9KaUhERWJ2VlJnUGhhZFJMeURoNDFtUmN0d0swbDFtWkFUcEVYR1ItUl9aRW1lcUZFUEFkS2lWYXhNdGp5aWM=");
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<Object> respEntity = restTemplate.exchange(
				"https://api.sandbox.paypal.com/v1/payments/billing-agreements/" + aggrementId, HttpMethod.GET, entity,
				Object.class);

		Object resp = respEntity.getBody();
		Map<String, String> campos = (Map<String, String>) resp;
		String state = campos.get("state");
		boolean isActive = false;
		if (state.equalsIgnoreCase("Active")) {
			isActive = true;
		}
		return isActive;

	}

}
