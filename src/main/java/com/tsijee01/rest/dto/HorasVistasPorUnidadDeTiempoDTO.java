package com.tsijee01.rest.dto;

import java.util.Date;

public class HorasVistasPorUnidadDeTiempoDTO {

	private Date fecha;
	
	private Long horasVistas;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getHorasVistas() {
		return horasVistas;
	}

	public void setHorasVistas(Long horasVistas) {
		this.horasVistas = horasVistas;
	}
	
}
