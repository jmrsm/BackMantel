package com.tsijee01.persistence.model;

public enum TipoPagoEnum {

	PAGO_SEMANAL("Pago semanal"), PAGO_MENSUAL("Pago mensual"), PAGO_ANUAL("Pago anual");

	private String tipoPago;

	TipoPagoEnum(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String url() {
		return tipoPago;
	}

}
