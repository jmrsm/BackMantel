package com.tsijee01.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "serie")
public class Serie extends Contenido {

	public Serie(Serie serie) {
		super((Contenido) serie);
	}
	
	public Serie(){
		
	}

	@Column(length = 512, nullable = false)
	private int temporada;
	
	@Column(length = 512, nullable = false)
	private int capitulo;

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}
	
}
