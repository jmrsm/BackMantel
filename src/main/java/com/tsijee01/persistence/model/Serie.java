package com.tsijee01.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "serie")
public class Serie extends Contenido {

	public Serie(Serie serie) {
		super((Contenido) serie);
	}

	@Column(length = 512, nullable = false)
	private int temporada;
	
	@Column(length = 512, nullable = false)
	private int capitulo;
	 
}
