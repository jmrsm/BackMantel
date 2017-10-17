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
	private String nombre;
	
	@Column(length = 512, nullable = false)
	private String apellido;
	
	@ManyToMany
	@JoinTable(name = "director_contenido",
			joinColumns=@JoinColumn(name="director_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="contenido_id", referencedColumnName="id"))
	private List <Contenido> contenido;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<Contenido> getContenido() {
		return contenido;
	}

	public void setContenido(List<Contenido> contenido) {
		this.contenido = contenido;
	}
	
}
