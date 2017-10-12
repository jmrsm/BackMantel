package com.tsijee01.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin_tenant")
public class AdminTenant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 512, nullable = false)
	private String passowd;
	
	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 50, nullable = false)
	private String apellido;

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "porveedor_id", nullable = false)
	private ProveedorContenido proveedorContenido;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassowd() {
		return passowd;
	}

	public void setPassowd(String passowd) {
		this.passowd = passowd;
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

	public ProveedorContenido getProveedorContenido() {
		return proveedorContenido;
	}

	public void setProveedorContenido(ProveedorContenido proveedorContenido) {
		this.proveedorContenido = proveedorContenido;
	}
	
}
