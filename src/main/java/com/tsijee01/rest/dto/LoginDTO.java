package com.tsijee01.rest.dto;

public class LoginDTO {
	
	private String TipoUsuario;
	
	private Long id;

	public String getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
