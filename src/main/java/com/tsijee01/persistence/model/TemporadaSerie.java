package com.tsijee01.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class TemporadaSerie {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column
	private int temporada;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.REMOVE})
	@JoinColumn(name = "id_capitulo")
	@Fetch (FetchMode.SELECT)
	private List <CapituloSerie> capitulos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public List<CapituloSerie> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<CapituloSerie> capitulos) {
		this.capitulos = capitulos;
	}
	
	
}
