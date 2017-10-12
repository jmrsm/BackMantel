package com.tsijee01.persistence.model;

public enum TipoUsuarioEnum {

	SUPER_ADMIN("Super_admin"), TENANT_ADMIN("Tenant_admin"), Usuario("Usuario"),Forbbiden("Forbbiden");

	private String tipoUsuario;

	TipoUsuarioEnum(String tipousuario) {
		this.tipoUsuario = tipousuario;
	}

	public String tipoUsuario() {
		return tipoUsuario;
	}

}
