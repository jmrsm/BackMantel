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

@Entity
public class HistorialContenido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column
	private boolean visto;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "id_contenido")
	private Contenido contenido;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "id_Usuario")
	private Usuario usuario;
	
	@Column(nullable = true)
	private int puntuacion;

	// en caso de que lo haya visto 
	@Column(nullable = true)
	private Long tiempoDeReproduccion;

	
	@Column(nullable = true)
	private boolean favorito;

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getTiempoDeReproduccion() {
		return tiempoDeReproduccion;
	}

	public void setTiempoDeReproduccion(Long tiempoDeReproduccion) {
		this.tiempoDeReproduccion = tiempoDeReproduccion;
	}

}
