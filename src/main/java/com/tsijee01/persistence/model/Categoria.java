package com.tsijee01.persistence.model;

import java.util.List;

public class Categoria {

	private String nombreCategoria;
	
	private List<Contenido> contenidoCategoria;

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Contenido> getContenidoCategoria() {
		return contenidoCategoria;
	}

	public void setContenidoCategoria(List<Contenido> contenidoCategoria) {
		this.contenidoCategoria = contenidoCategoria;
	}
	
}
