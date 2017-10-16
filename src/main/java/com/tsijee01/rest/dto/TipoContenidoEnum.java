package com.tsijee01.rest.dto;

public enum TipoContenidoEnum {

	PELICULA("PELICULA"), SERIE("SERIE"), EVENTO_DEPORTIVO("EVENTO_DEPORTIVO"),EVENTO_ESPECTACULO("EVENTO_ESPECTACULO");

	private String tipoContenido;

	TipoContenidoEnum(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public String tipoContenido() {
		return tipoContenido;
	}

}
