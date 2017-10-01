package com.tsijee01.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "pelicula")
public class Pelicula extends Contenido {

	@Column(nullable = false)
	private int cantMinutos;
}
