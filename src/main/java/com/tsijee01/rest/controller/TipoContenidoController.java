package com.tsijee01.rest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.rest.dto.TipoContenidoEnum;
import com.tsijee01.service.ContenidoService;
import com.tsijee01.service.SuperAdminService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class TipoContenidoController {

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/tipoContenido", method = RequestMethod.GET)
	public ResponseEntity<List<String>> obtenerCategoriasContenido(HttpServletRequest request) {

//		String mailSuperAdmin = (String) request.getSession()
//				.getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin==null){
//			return new ResponseEntity<List<String>>(HttpStatus.FORBIDDEN);
//		}
//		
		List<String> tiposContenido = Arrays.asList(TipoContenidoEnum.values()).stream().map(tc -> tc.name()).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(tiposContenido, HttpStatus.OK);

	}

}
