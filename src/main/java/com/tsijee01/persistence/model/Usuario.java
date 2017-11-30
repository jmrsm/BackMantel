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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_historial_ontenido")
	@Fetch(FetchMode.SELECT)
	List<HistorialContenido> historialContenido;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_historial_pagos")
	@Fetch(FetchMode.SELECT)
	List<HistorialPagos> historialPagos;

	// el medio de pago que está usando actualmente, el que se va a user para
	// realizar el siguiente pago
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_medio_pago")
	private MedioDePago medioDePago;

	// el ultimo pago realizado se usa para ver si está en al dia con los pagos,
	// también forma parte del historial de pagos
	@OneToOne
	@JoinColumn(name = "id_ultimo_pago")
	@Fetch(FetchMode.SELECT)
	private HistorialPagos ultimoPago;

	@Column(name= "agreementId", length = 512, nullable = true)
	private String agreementId;
	
	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 512, nullable = false)
	private String passowd;

	@Column(length = 50, nullable = false)
	private String nombre;

	@Column(length = 50, nullable = false)
	private String apellido;

	@Column
	private boolean habilitado;

	@Column(name= "gmail_token", length = 512, nullable = true)
	private String gmailToken;

	
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

	public List<HistorialContenido> getHistorialContenido() {
		return historialContenido;
	}

	public void setHistorialContenido(List<HistorialContenido> historialContenido) {
		this.historialContenido = historialContenido;
	}

	public List<HistorialPagos> getHistorialPagos() {
		return historialPagos;
	}

	public void setHistorialPagos(List<HistorialPagos> historialPagos) {
		this.historialPagos = historialPagos;
	}

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public HistorialPagos getUltimoPago() {
		return ultimoPago;
	}

	public void setUltimoPago(HistorialPagos ultimoPago) {
		this.ultimoPago = ultimoPago;
	}

	public String getGmailToken() {
		return gmailToken;
	}

	public void setGmailToken(String gmailToken) {
		this.gmailToken = gmailToken;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	
}
