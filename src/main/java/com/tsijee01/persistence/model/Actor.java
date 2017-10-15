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
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 512, nullable = false)
	private String apellido;
	
	@ManyToMany
	@JoinTable(name = "actor_contenido",
			joinColumns=@JoinColumn(name="contenido_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="id"))
	private List <Contenido> contenidos;

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

	public List<Contenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List<Contenido> contenidos) {
		this.contenidos = contenidos;
	}
	
	
}
