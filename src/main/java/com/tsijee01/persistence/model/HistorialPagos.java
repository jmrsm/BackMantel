package com.tsijee01.persistence.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class HistorialPagos {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_historial_medio_pago")
	@Fetch (FetchMode.SELECT)
	private MedioDePago medioDePago;

	@Column
	private Date fechaPago;
	
	@Column
	private TipoPagoEnum tipoPago;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public TipoPagoEnum getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPagoEnum tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	
	
}
