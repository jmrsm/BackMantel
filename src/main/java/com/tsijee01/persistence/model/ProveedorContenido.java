package com.tsijee01.persistence.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProveedorContenido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String nombre;
	
//	private List<PaqueteContenido> paqueteContenido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public List<PaqueteContenido> getPaqueteContenido() {
//		return paqueteContenido;
//	}
//
//	public void setPaqueteContenido(List<PaqueteContenido> paqueteContenido) {
//		this.paqueteContenido = paqueteContenido;
//	}
	
}
