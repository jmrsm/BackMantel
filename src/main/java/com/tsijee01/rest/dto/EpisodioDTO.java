package com.tsijee01.rest.dto;

import com.tsijee01.persistence.model.TemporadaSerie;

public class EpisodioDTO {

	private long id;

	private String path;
	
	private TemporadaSerie temporada;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public long getIdTemporada() {
		return this.temporada.getId();
	}
	
	public void setTemporada(TemporadaSerie temporada) {
		this.temporada= temporada;
	}
}
