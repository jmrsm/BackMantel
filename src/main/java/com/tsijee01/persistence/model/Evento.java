package com.tsijee01.persistence.model;

public abstract class Evento extends Contenido {

	public Evento(Evento evento) {
		super((Contenido) evento);
	}

}
