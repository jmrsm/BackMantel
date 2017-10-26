package com.tsijee01.rest.dto;

public class CategoriaDTO {
	
	private long id;
	
	private String nombreCategoria;

	public CategoriaDTO(){
		
	}
	
	public CategoriaDTO(String nombreCategoria){
		this.nombreCategoria = nombreCategoria;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
}
