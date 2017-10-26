package com.tsijee01.rest.dto;

public class ActorDTO {

	private String nombreCompleto;

	public ActorDTO(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public ActorDTO() {

	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
}
