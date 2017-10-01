package com.tsijee01.persistence.model;

public class Comentario {

	public Usuario usuario;
	
	public String comentario;
	
	public int esSpoiler;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getEsSpoiler() {
		return esSpoiler;
	}

	public void setEsSpoiler(int esSpoiler) {
		this.esSpoiler = esSpoiler;
	}
	
	
}
