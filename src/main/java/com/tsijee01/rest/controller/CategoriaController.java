package com.tsijee01.rest.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.rest.dto.CategoriaDTO;
import com.tsijee01.service.CategoriaContenidoService;
import com.tsijee01.service.SuperAdminService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class CategoriaController {

	@Autowired
	private SuperAdminService administradorService;

	@Autowired
	private SuperAdminService superAdminService;

	@Autowired
	private CategoriaContenidoService categoriaContenidoService;

	@Autowired
	private MapperFacade mapper;

	// crear nueva categoria de contenido
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/categoriaContenido", method = RequestMethod.POST)
	public ResponseEntity<?> altaCategoriaContenido(HttpServletRequest request,
			@RequestParam(name = "nombre", required = true) String nombreCategoria) {

		String mailSuperAdmin = (String) request.getSession()
				.getAttribute("SUPER_ADMIN");
		if (mailSuperAdmin==null){
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		if (categoriaContenidoService.altaCategoriaContenido(nombreCategoria)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
	}

	// crear nueva categoria de contenido
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/categoriaContenido", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> obtenerCategoriasContenido(HttpServletRequest request) {
		String mailSuperAdmin = (String) request.getSession()
				.getAttribute("SUPER_ADMIN");
		if (mailSuperAdmin==null){
			return new ResponseEntity<List<CategoriaDTO>>(HttpStatus.FORBIDDEN);
		}
		List<CategoriaDTO> cates = mapper.mapAsList(categoriaContenidoService.obtenerCategorias(), CategoriaDTO.class);
		return new ResponseEntity<List<CategoriaDTO>>(cates, HttpStatus.OK);

	}
}
