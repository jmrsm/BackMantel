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
}
