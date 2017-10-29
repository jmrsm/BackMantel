package com.tsijee01.persistence.model;

import javax.persistence.Entity;

@Entity(name = "pelicula")
public class Pelicula extends Contenido {

	public Pelicula(Pelicula peli) {
		super((Contenido) peli);
	}

	public Pelicula() {

	}

}
