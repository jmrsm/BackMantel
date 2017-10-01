package com.tsijee01.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contenido {
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "proveedorId")
	ProveedorContenido proveedorContenido;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(nullable = false)
	private Date fechaPublicado;
	
	@Column(length = 100, nullable = false)
	private String titulo;
	
	@Column(length = 512, nullable = false)
	private String descipcion;
	
	@Transient
	private List<Categoria> categorias;
	
	private int ranking;
	
	@Transient
	private List<String> elenco;
	
	@Transient
	private List<String> directores;
	
	
	private String fotoPortada;
	
	@Transient
	private List<String> comentarios;
	
	@Transient
	private List<Contenido> similares;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPublicado() {
		return fechaPublicado;
	}

	public void setFechaPublicado(Date fechaPublicado) {
		this.fechaPublicado = fechaPublicado;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public List<String> getElenco() {
		return elenco;
	}

	public void setElenco(List<String> elenco) {
		this.elenco = elenco;
	}

	public List<String> getDirectores() {
		return directores;
	}

	public void setDirectores(List<String> directores) {
		this.directores = directores;
	}

	public String getFotoPortada() {
		return fotoPortada;
	}

	public void setFotoPortada(String fotoPortada) {
		this.fotoPortada = fotoPortada;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Contenido> getSimilares() {
		return similares;
	}

	public void setSimilares(List<Contenido> similares) {
		this.similares = similares;
	}

	public ProveedorContenido getProveedorContenido() {
		return proveedorContenido;
	}

	public void setProveedorContenido(ProveedorContenido proveedorContenido) {
		this.proveedorContenido = proveedorContenido;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
