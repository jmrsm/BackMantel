package com.tsijee01.rest.dto;

import com.tsijee01.persistence.model.TemporadaSerie;

public class EpisodioDTO {

	private long id;
	
	private int capitulo;

	private String path;
	
	private TemporadaSerie temporada;
	
	private int temporadaN;

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

	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}

	public int getTemporadaN() {
		return temporadaN;
	}

	public void setTemporadaN(int temporadaN) {
		this.temporadaN = temporadaN;
	}

}
