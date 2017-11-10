package com.tsijee01.rest.dto;

import java.util.List;

public class ComentarioDTO {

	private long id;

	public List<ComentarioDTO> respuestas;
	
	public UsuarioDTO usuario;
	
	public String contenidoComentario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ComentarioDTO> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<ComentarioDTO> respuestas) {
		this.respuestas = respuestas;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getContenidoComentario() {
		return contenidoComentario;
	}

	public void setContenidoComentario(String contenidoComentario) {
		this.contenidoComentario = contenidoComentario;
	}
	
}
