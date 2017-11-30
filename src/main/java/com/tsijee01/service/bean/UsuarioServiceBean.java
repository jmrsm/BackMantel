package com.tsijee01.service.bean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.paypal.base.codec.binary.Base64;
import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.AdminTenantRepository;
import com.tsijee01.persistence.repository.HistorialContenidoRepository;
import com.tsijee01.persistence.repository.SuperAdminRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.UsuarioService;
import com.tsijee01.util.Password;

@Service
public class UsuarioServiceBean implements UsuarioService{

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${paypalClientId}")
	private String paypalClientId;

	@Value("${paypalSecret}")
	private String paypalSecret;

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private AdminTenantRepository adminTenantRepository; 
	
	@Autowired
	private SuperAdminRepository superadminRepository; 
	
	@Autowired
	private Password passwordUtil;
	
	@Autowired
	private HistorialContenidoRepository historialContenidoRepository; 
	

	@Override
	public Page<Usuario> listarUsuarios(Pageable pag) {
		return usuarioRepository.findAll(pag);
	}

	@Override
	public Page<Usuario> buscarPorMail(Pageable pag, String query) {
		return usuarioRepository.findByEmailContaining(pag, query);
		
	}

	@Override
	public Usuario buscarPorId(Long usuarioId) {
		return usuarioRepository.findOne(usuarioId).get();
	}
	
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findHabilitadosConUltimoPago() {
		return usuarioRepository.findByHabilitadoTrue();
	}

	@Override
	public List<Usuario> altaUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String existeUser(String email) {
		// TODO Auto-generated method stub
		if(usuarioRepository.existsByEmail(email)){
			return "Existe";
		}else{
			return "No existe";
		}
			
		
	}
	public boolean login(String email, String password) {

		Optional<Usuario> sa = usuarioRepository.findOneByEmail(email);
		
		if (sa.isPresent()) {
			if (passwordUtil.checkearPassword(password, sa.get().getPassowd())){
				// TODO loguear el usuario al sistema 
				return true;
			}
			
		}
		return false;
	}
	@Override
	public boolean crearUser(String email, String password, String nombre, String apellido) {

		Optional<SuperAdmin> sa = superadminRepository.findOneByEmail(email);
		if (sa.isPresent()){
			return false;
		}
		Optional<AdminTenant> at = adminTenantRepository.findOneByEmail(email);
		if (at.isPresent()){
			return false;
		}
		Optional<Usuario> u = usuarioRepository.findOneByEmail(email);
		if(u.isPresent()){
			//Si el usuario ya existe
			return false;
		}else{
			Usuario user= new Usuario();
			user.setApellido(apellido);
			user.setEmail(email);
			user.setHabilitado(true);
			user.setNombre(nombre);
			user.setPassowd(passwordUtil.hasherPassword(password));
			usuarioRepository.save(user);
			return true;
		}
		
	}

	@Override
	public List<Contenido> listarFavoritos(Long id) {
		Optional <Usuario> u = usuarioRepository.findOne(id);
		List<HistorialContenido> listaHC = historialContenidoRepository.findByFavoritoAndUsuario(true, u.get());
		return listaHC.stream().map(x->x.getContenido()).collect(Collectors.toList());
		
	}

	@Override
	public boolean crearSuscripcion(String email, String paypalToken) {
		
		Optional<Usuario> u = usuarioRepository.findByEmail(email);
		u.get().setAgreementId(paypalToken);
		usuarioRepository.save(u.get());
		return true;
	}
	
//	public static Agreement get(APIContext apiContext, String agreementId) throws PayPalRESTException {
//
//		if (agreementId == null) {
//			throw new IllegalArgumentException("agreementId cannot be null");
//		}
//		Object[] parameters = new Object[] {agreementId};
//		String pattern = "v1/payments/billing-agreements/{0}";
//		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
//		String payLoad = "";
//		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, Agreement.class);
//	}


	
//	public Optional<Usuario> altaUsuario(Usuario dtos) {
//		Optional<SuperAdmin> sa = superadminRepository.findOneByEmail(email);
//		if (sa.isPresent()){
//			return Optional;
//		}
//		Optional<AdminTenant> at = adminTenantRepository.findOneByEmail(email);
//		if (at.isPresent()){
//			return false;
//		}
//		
//		
//		if (dtos.getGmailToken().isEmpty() && dtos.getGmailToken().length() < 3){
//			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(dtos.getPassword());
//			dtos.setPassowd(passwordUtil.hasherPassword(password));
//		}else {
//			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(dtos.getGmailToken());
//			dtos.setGmailToken(sha256hex);
//		}
//		if (usuarioRepository.findByEmail(dtos.getEmail()).isPresent()){
//			return null; 
//		}else{
//			return Optional.of(usuarioRepository.save(dtos));
//		}
//	}
	
}

