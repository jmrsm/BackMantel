package com.tsijee01.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.REMOVE})
	@JoinColumn(name = "id_respuesta")
	@Fetch (FetchMode.SELECT)
	public List<Comentario> respuestas;
	
	@OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.REMOVE})
	@JoinColumn(name = "id_responde_comentario")
	@Fetch (FetchMode.SELECT)
	public Comentario respondeA;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_contenido", nullable = false)
	public Contenido contenido;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
	public Usuario usuario;
	
	@Column(nullable = false)
	public String contenidoComentario;
	
	@ManyToMany
	@JoinTable(name = "comentario_spoiler",
			joinColumns=@JoinColumn(name="comentario_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="usuario_id", referencedColumnName="id"))
	public List<Usuario> marcaSpoiler;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Comentario> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Comentario> respuestas) {
		this.respuestas = respuestas;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public List<Usuario> getMarcaSpoiler() {
		return marcaSpoiler;
	}

	public void setMarcaSpoiler(List<Usuario> marcaSpoiler) {
		this.marcaSpoiler = marcaSpoiler;
	}

	public String getContenidoComentario() {
		return contenidoComentario;
	}

	public void setContenidoComentario(String contenidoComentario) {
		this.contenidoComentario = contenidoComentario;
	}

	public Comentario getRespondeA() {
		return respondeA;
	}

	public void setRespondeA(Comentario respondeA) {
		this.respondeA = respondeA;
	}
	
}
