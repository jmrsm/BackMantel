package com.tsijee01.rest.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorDTO {

	public DirectorDTO() {

	}

	public DirectorDTO(String nombreyApellido) {
		this.nombreCompleto = nombreyApellido;
	}

	private long id;

	private String nombreCompleto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}
