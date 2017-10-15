package com.tsijee01.rest.dto;

import java.util.List;

public class ProveedorContenidoDTO {

	private Long id;
	
	private String nombre;

	private List<AdminTenantDTO> admins;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AdminTenantDTO> getAdmins() {
		return admins;
	}

	public void setAdmins(List<AdminTenantDTO> admins) {
		this.admins = admins;
	}
	
	
	
}