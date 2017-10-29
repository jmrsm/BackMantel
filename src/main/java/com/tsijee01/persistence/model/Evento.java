package com.tsijee01.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Evento extends Contenido {

	public Evento(Evento evento) {
		super((Contenido) evento);
	}

	public Evento() {
	}

	@Column(nullable = false)
	private Boolean esPago;

	@Column(nullable = false)
	private int precio;

	@Column(nullable = false)
	private Date fechaInicio;

	public Boolean getEsPago() {
		return esPago;
	}

	public void setEsPago(Boolean esPago) {
		this.esPago = esPago;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

}
