package com.tsijee01.persistence.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column
	private String nombreCategoria;
	
	@ManyToMany
	@JoinTable(name = "categoria_contenido",
			joinColumns=@JoinColumn(name="categoria_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="contenido_id", referencedColumnName="id"))
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
