package com.tsijee01.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchContenidoOmbdapi {

	@JsonProperty("Search")
	List<ContenidoBusquedaOMDbDTO> resultadoBusqueda;

	public List<ContenidoBusquedaOMDbDTO> getResultadoBusqueda() {
		return resultadoBusqueda;
	}

	public void setResultadoBusqueda(List<ContenidoBusquedaOMDbDTO> resultadoBusqueda) {
		this.resultadoBusqueda = resultadoBusqueda;
	}
	
}
