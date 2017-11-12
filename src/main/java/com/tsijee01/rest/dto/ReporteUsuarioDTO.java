package com.tsijee01.rest.dto;

import java.util.List;

public class ReporteUsuarioDTO {

	private long horasTotalesPerdidas;
	
	private List<HorasPorCategoriaDTO> horasPorCategoria;
	
	private List<ContenidoBasicDTO> contenidoFavoritoNoVisto;
	
	private List<ContenidoBasicDTO> contenidoMejorPuntuadoNoVisto;

	public long getHorasTotalesPerdidas() {
		return horasTotalesPerdidas;
	}

	public void setHorasTotalesPerdidas(long horasTotalesPerdidas) {
		this.horasTotalesPerdidas = horasTotalesPerdidas;
	}

	public List<HorasPorCategoriaDTO> getHorasPorCategoria() {
		return horasPorCategoria;
	}

	public void setHorasPorCategoria(List<HorasPorCategoriaDTO> horasPorCategoria) {
		this.horasPorCategoria = horasPorCategoria;
	}

	public List<ContenidoBasicDTO> getContenidoFavoritoNoVisto() {
		return contenidoFavoritoNoVisto;
	}

	public void setContenidoFavoritoNoVisto(List<ContenidoBasicDTO> contenidoFavoritoNoVisto) {
		this.contenidoFavoritoNoVisto = contenidoFavoritoNoVisto;
	}

	public List<ContenidoBasicDTO> getContenidoMejorPuntuadoNoVisto() {
		return contenidoMejorPuntuadoNoVisto;
	}

	public void setContenidoMejorPuntuadoNoVisto(List<ContenidoBasicDTO> contenidoMejorPuntuadoNoVisto) {
		this.contenidoMejorPuntuadoNoVisto = contenidoMejorPuntuadoNoVisto;
	}
	
	
}
