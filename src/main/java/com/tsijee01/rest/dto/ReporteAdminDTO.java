package com.tsijee01.rest.dto;

import java.util.List;

public class ReporteAdminDTO {

	private long horasTotalesVisualizadas;
	
	private long cantidadVisualizacionesTotales;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorDia;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorSemana;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorMes;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorAnio;

	
	public long getHorasTotalesVisualizadas() {
		return horasTotalesVisualizadas;
	}

	public void setHorasTotalesVisualizadas(long horasTotalesVisualizadas) {
		this.horasTotalesVisualizadas = horasTotalesVisualizadas;
	}

	public List<HorasVistasPorUnidadDeTiempoDTO> getHorasVistasPorDia() {
		return horasVistasPorDia;
	}

	public void setHorasVistasPorDia(List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorDia) {
		this.horasVistasPorDia = horasVistasPorDia;
	}

	public List<HorasVistasPorUnidadDeTiempoDTO> getHorasVistasPorSemana() {
		return horasVistasPorSemana;
	}

	public void setHorasVistasPorSemana(List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorSemana) {
		this.horasVistasPorSemana = horasVistasPorSemana;
	}

	public List<HorasVistasPorUnidadDeTiempoDTO> getHorasVistasPorMes() {
		return horasVistasPorMes;
	}

	public void setHorasVistasPorMes(List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorMes) {
		this.horasVistasPorMes = horasVistasPorMes;
	}

	public List<HorasVistasPorUnidadDeTiempoDTO> getHorasVistasPorAnio() {
		return horasVistasPorAnio;
	}

	public void setHorasVistasPorAnio(List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorAnio) {
		this.horasVistasPorAnio = horasVistasPorAnio;
	}

	public long getCantidadVisualizacionesTotales() {
		return cantidadVisualizacionesTotales;
	}

	public void setCantidadVisualizacionesTotales(long cantidadVisualizacionesTotales) {
		this.cantidadVisualizacionesTotales = cantidadVisualizacionesTotales;
	}

}
