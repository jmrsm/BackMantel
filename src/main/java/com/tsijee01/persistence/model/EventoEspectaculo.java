package com.tsijee01.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EventoEspectaculo extends Evento {

	
	@Column(nullable = false)
	private Date fechaInicio;
	
	public EventoEspectaculo(EventoEspectaculo eventoEspectaculo) {
		super((Evento)eventoEspectaculo);
	}
	

}
