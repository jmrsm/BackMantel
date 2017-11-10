package com.tsijee01.rest.dto;

public class ProveedorCantidadHorasDTO {

	private String nombreProveedor;
	
	private Long CantidadHoras;

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public Long getCantidadHoras() {
		return CantidadHoras;
	}

	public void setCantidadHoras(Long cantidadHoras) {
		CantidadHoras = cantidadHoras;
	}
	
}
