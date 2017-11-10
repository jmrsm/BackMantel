package com.tsijee01.rest.dto;

import java.util.List;

public class ReporteSuperAdminDTO {
	

	private long cantuUsuarioTotales;

	private long cantuUsuarioHabilitados;
	
	private long horasTotalesVisualizadas;
	
	private List<PorveedorCantidadDTO> proveedorCantidad;
	
	private List<ProveedorCantidadHorasDTO> proveedorCantidadHoras;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorDia;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorSemana;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorMes;
	
	private List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorAnio;
	

	public long getCantuUsuarioTotales() {
		return cantuUsuarioTotales;
	}

	public void setCantuUsuarioTotales(long cantuUsuarioTotales) {
		this.cantuUsuarioTotales = cantuUsuarioTotales;
	}

	public long getCantuUsuarioHabilitados() {
		return cantuUsuarioHabilitados;
	}

	public void setCantuUsuarioHabilitados(long cantuUsuarioHabilitados) {
		this.cantuUsuarioHabilitados = cantuUsuarioHabilitados;
	}

	public long getHorasTotalesVisualizadas() {
		return horasTotalesVisualizadas;
	}

	public void setHorasTotalesVisualizadas(long horasTotalesVisualizadas) {
		this.horasTotalesVisualizadas = horasTotalesVisualizadas;
	}

	public List<PorveedorCantidadDTO> getProveedorCantidad() {
		return proveedorCantidad;
	}

	public void setProveedorCantidad(List<PorveedorCantidadDTO> proveedorCantidad) {
		this.proveedorCantidad = proveedorCantidad;
	}

	public List<ProveedorCantidadHorasDTO> getProveedorCantidadHoras() {
		return proveedorCantidadHoras;
	}

	public void setProveedorCantidadHoras(List<ProveedorCantidadHorasDTO> proveedorCantidadHoras) {
		this.proveedorCantidadHoras = proveedorCantidadHoras;
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
	
}
