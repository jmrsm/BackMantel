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

import com.tsijee01.persistence.model.Comentario;
import com.tsijee01.rest.dto.ComentarioDTO;
import com.tsijee01.service.ComentarioService;
import com.tsijee01.service.ContenidoService;

import ma.glasnost.orika.MapperFacade;

@RestController
public class ComentarioController {
	@Autowired
	private MapperFacade mapper;

	@Autowired
	private ContenidoService contenidoService;

	@Autowired
	private ComentarioService comentarioService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/comentarComentario", method = RequestMethod.POST)
	public ResponseEntity<?> comentarComentario(HttpServletRequest request,
			@RequestParam(name = "contenidoId", required = true) Long contenidoId,
			@RequestParam(name = "usuarioId", required = true) Long usuarioId,
			@RequestParam(name = "comentarioPadreId", required = true) Long comentarioPadreId,
			@RequestParam(name = "comentario", required = true) String comentario) {

//		String mailUsuario = (String) request.getSession()
//				.getAttribute("USUARIO");
//		if (mailUsuario==null){
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}
		
		comentarioService.comentarComentario(contenidoId, comentario, comentarioPadreId, usuarioId);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/listarComentarios", method = RequestMethod.GET)
	public ResponseEntity<List<ComentarioDTO>> listarComentarios(HttpServletRequest request,
			@RequestParam(name = "idContenido", required = true) Long contenidoId) {

//		String mailUsuario = (String) request.getSession()
//				.getAttribute("USUARIO");
//		if (mailUsuario==null){
//			return new ResponseEntity<List<ComentarioDTO>>(HttpStatus.FORBIDDEN);
//		}
		
		List<Comentario> comentarios = comentarioService.listarComentarios(contenidoId);
		return new ResponseEntity<List<ComentarioDTO>>(mapper.mapAsList(comentarios, ComentarioDTO.class), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/marcarSpoiler", method = RequestMethod.PUT)
	public ResponseEntity<?> marcarSpoiler(HttpServletRequest request,
			@RequestParam(name = "idComentario", required = true) Long comentarioId,
			@RequestParam(name = "idUsuario", required = true) Long usuarioId) {

//		String mailUsuario = (String) request.getSession()
//				.getAttribute("USUARIO");
//		if (mailUsuario==null){
//			return new ResponseEntity<List<ComentarioDTO>>(HttpStatus.FORBIDDEN);
//		}
		
		contenidoService.marcarSpoiler(comentarioId,  usuarioId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
