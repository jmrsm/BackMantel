package com.tsijee01.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "serie")
public class Serie extends Contenido {

	public Serie(Serie serie) {
		super((Contenido) serie);
	}
	
	public Serie(){
		
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.REMOVE})
	@JoinColumn(name = "id_temporada")
	@Fetch (FetchMode.SELECT)
	private List<TemporadaSerie> temporadas;

	public List<TemporadaSerie> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<TemporadaSerie> temporadas) {
		this.temporadas = temporadas;
	}
	
	
}
