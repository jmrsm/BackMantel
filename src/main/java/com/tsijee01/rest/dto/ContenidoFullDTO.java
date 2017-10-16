package com.tsijee01.rest.dto;

import java.util.List;

public class ContenidoFullDTO {

	private long id;

	private String titulo;
	
	private String descipcion;
	
	private Integer duracion;
	
	private List<CategoriaDTO> categorias;
	
	private List <DirectorDTO> directores;

	private List <ActorDTO> actores;
	
	private TipoContenidoEnum tipoContenido;
	
	private ProveedorContenidoBasicDTO proveedorContenido;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public List<CategoriaDTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}

	public List<DirectorDTO> getDirectores() {
		return directores;
	}

	public void setDirectores(List<DirectorDTO> directores) {
		this.directores = directores;
	}

	public List<ActorDTO> getActores() {
		return actores;
	}

	public void setActores(List<ActorDTO> actores) {
		this.actores = actores;
	}

	public TipoContenidoEnum getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(TipoContenidoEnum tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public ProveedorContenidoBasicDTO getProveedorContenido() {
		return proveedorContenido;
	}

	public void setProveedorContenido(ProveedorContenidoBasicDTO proveedorContenido) {
		this.proveedorContenido = proveedorContenido;
	}
	
}
