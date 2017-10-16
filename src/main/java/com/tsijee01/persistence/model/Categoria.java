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

	public List<Contenido> getContenidoCategoria() {
		return contenidoCategoria;
	}

	public void setContenidoCategoria(List<Contenido> contenidoCategoria) {
		this.contenidoCategoria = contenidoCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
