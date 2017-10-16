package com.tsijee01.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EventoDeportivo extends Evento {

	@Column(length = 50, nullable = false)
	private String nombreEquipoVisitante;

	@Column(length = 50, nullable = false)
	private String nombreEquipoLocal;

	@Column(length = 50, nullable = false)
	private String nombreDeporte;

	public EventoDeportivo(EventoDeportivo eventoDeportivo) {
		super((Evento) eventoDeportivo);
	}

	public String getNombreEquipoVisitante() {
		return nombreEquipoVisitante;
	}

	public void setNombreEquipoVisitante(String nombreEquipoVisitante) {
		this.nombreEquipoVisitante = nombreEquipoVisitante;
	}

	public String getNombreEquipoLocal() {
		return nombreEquipoLocal;
	}

	public void setNombreEquipoLocal(String nombreEquipoLocal) {
		this.nombreEquipoLocal = nombreEquipoLocal;
	}

	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

}
