package com.tsijee01.rest.dto;

public class PorveedorCantidadDTO {

	private String nombreProceedor;
	
	private Long cantidadContenido;

	public String getNombreProceedor() {
		return nombreProceedor;
	}

	public void setNombreProceedor(String nombreProceedor) {
		this.nombreProceedor = nombreProceedor;
	}

	public Long getCantidadContenido() {
		return cantidadContenido;
	}

	public void setCantidadContenido(Long cantidadContenido) {
		this.cantidadContenido = cantidadContenido;
	}
	
}
