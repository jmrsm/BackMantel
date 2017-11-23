package com.tsijee01.persistence.model;

public enum TipoUsuarioEnum {

	SUPER_ADMIN("Super_admin"), TENANT_ADMIN("Tenant_admin"), USUARIO("Usuario"),Forbbiden("Forbbiden"),NO_PAGO("No_pago") ;

	private String tipoUsuario;

	TipoUsuarioEnum(String tipousuario) {
		this.tipoUsuario = tipousuario;
	}

	public String tipoUsuario() {
		return tipoUsuario;
	}

}
