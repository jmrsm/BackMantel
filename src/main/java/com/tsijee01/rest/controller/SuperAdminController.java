package com.tsijee01.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.rest.dto.AdminTenantDTO;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.rest.dto.ProveedorContenidoDTO;
import com.tsijee01.rest.dto.SuperAdminDTO;
import com.tsijee01.rest.dto.UsuarioDTO;
import com.tsijee01.service.ProveedorContenidoService;
import com.tsijee01.service.SuperAdminService;
import com.tsijee01.service.UsuarioService;
import com.tsijee01.util.PageUtils;

import ma.glasnost.orika.MapperFacade;

@RestController
public class SuperAdminController {

	@Autowired
	private SuperAdminService administradorService;

	@Autowired
	private SuperAdminService superAdminService;

	@Autowired
	private ProveedorContenidoService proveedorContenidoService;

	@Autowired
	private MapperFacade mapper;

	@Autowired
	private UsuarioService usuarioService;

	// Esto no puede ser publica hay que borrarla antes de entregar
	// API para crear super Admin
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/superAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> altaSuperAdministrador(HttpServletRequest request,
			@RequestBody SuperAdminDTO superAdminDTO,
			@RequestParam(name = "password", required = true) String password) {

		if (superAdminService.altaSuperAdmin(mapper.map(superAdminDTO, SuperAdmin.class), password)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/admin", method = RequestMethod.POST)
	public ResponseEntity<?> altaAdminTenant(HttpServletRequest request,
			@RequestParam(name = "apellido", required = true) String apellido,
 			@RequestParam(name = "email", required = true) String email,
 			@RequestParam(name = "nombre", required = true) String nombre,
 			@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "proveedorContenidoId", required = true) Long proveedorContenidoId) {

//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		AdminTenantDTO a = new AdminTenantDTO();
		a.setApellido(apellido);
		a.setEmail(email);
		a.setId(id);
		a.setNombre(nombre);

		if (administradorService.altaAdmin(mapper.map(a, AdminTenant.class), proveedorContenidoId,
  				password)){
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	// obtener todos los proveedores de contenido junto con sus administradores
	@RequestMapping(path = "api/superAdmin/proveedorContenido", method = RequestMethod.GET)
	public ResponseEntity<List<ProveedorContenidoDTO>> obtenerAdministradorContenido(HttpServletRequest request) {

//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<List<ProveedorContenidoDTO>>(HttpStatus.FORBIDDEN);
//		}
		return new ResponseEntity<List<ProveedorContenidoDTO>>(
				mapper.mapAsList(proveedorContenidoService.findAll(), ProveedorContenidoDTO.class), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	// obtener todos los usuarios finales del sistema
	@RequestMapping(path = "api/superAdmin/usuarios", method = RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> obtenerusuarios(HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query) {

//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Page<UsuarioDTO>>(HttpStatus.FORBIDDEN);
//		}

		Pageable pag = PageUtils.getPageRequest(start, end, sortField, sortOrder);
		Page<UsuarioDTO> dtoPage;
		if (StringUtils.isEmpty(query)) {
			dtoPage = usuarioService.listarUsuarios(pag).map(new Converter<Usuario, UsuarioDTO>() {
				@Override
				public UsuarioDTO convert(Usuario peli) {
					return mapper.map(peli, UsuarioDTO.class);
				}
			});
		} else {
			dtoPage = usuarioService.buscarPorMail(pag, query).map(new Converter<Usuario, UsuarioDTO>() {
				@Override
				public UsuarioDTO convert(Usuario peli) {
					return mapper.map(peli, UsuarioDTO.class);
				}
			});
		}
		return new ResponseEntity<Page<UsuarioDTO>>(dtoPage, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	// obtener todos los usuarios finales del sistema
	@RequestMapping(path = "api/superAdmin/usuario", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> obtenerusuarios(HttpServletRequest request,
			@RequestParam(name = "usuarioId", required = true) Long usuarioId) {
//
//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<UsuarioDTO>(HttpStatus.FORBIDDEN);
//		}

		Usuario u = usuarioService.buscarPorId(usuarioId);
		return new ResponseEntity<UsuarioDTO>(mapper.map(u, UsuarioDTO.class), HttpStatus.OK);
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	// crear nuevo proveedor de contenido
	@RequestMapping(path = "api/superAdmin/proveedorContenido", method = RequestMethod.POST)
	public ResponseEntity<?> altaProveedorContenido(HttpServletRequest request,
			@RequestParam(name = "nombre", required = true) String nombreProveedorContenido) {
		
//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}

		if (proveedorContenidoService.altaProveedorContenido(nombreProveedorContenido)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/bloquearUsuario", method = RequestMethod.PUT)
	public ResponseEntity<?> bloquearUsuario(HttpServletRequest request, 
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "bloquear", required = true) Boolean habilitado) {
		
//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		
		superAdminService.bloquearUsuario(email, habilitado);
		return null;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/bloquearContenido", method = RequestMethod.PUT)
	public ResponseEntity<?> bloquearContenido(HttpServletRequest request, 
			@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "bloquear", required = true) Boolean bloquear) {
		
//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		
		Long i = Long.parseLong(id);
		superAdminService.bloquearContenido(i, bloquear);
		return null;
	}
	
	
	
}

// @RequestMapping(path = "api/superAdmin/proveedorContenido/{id}", method =
// RequestMethod.GET)
// public ResponseEntity<List<ProveedorContenidoDTO>>
// altaAdministradorContenido(HttpServletRequest request) {
//
// return new ResponseEntity<List<ProveedorContenidoDTO>>(
// mapper.mapAsList(proveedorContenidoService.findAll(),
// ProveedorContenidoDTO.class), HttpStatus.OK);
// }

// actualizar un proveedor de contenido
// @RequestMapping(path = "api/superAdmin/proveedorContenido", method =
// RequestMethod.PUT)
// public ResponseEntity<?> updateAdministradorContenido(HttpServletRequest
// request,
// @RequestBody ProveedorContenidoDTO proveedorContenidoDTO) {
//
// if (proveedorContenidoService
// .actualizarProveedorContenido(mapper.map(proveedorContenidoDTO,
// ProveedorContenido.class))) {
// return new ResponseEntity<Object>(HttpStatus.OK);
// } else {
// return new ResponseEntity<Object>(HttpStatus.CONFLICT);
// }
// }
