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
public class Director {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(length = 50, nullable = false)
	private String nombreCompleto;

	@ManyToMany
	@JoinTable(name = "director_contenido", joinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "contenido_id", referencedColumnName = "id"))
	private List<Contenido> contenido;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public List<Contenido> getContenido() {
		return contenido;
	}

	public void setContenido(List<Contenido> contenido) {
		this.contenido = contenido;
	}

}
