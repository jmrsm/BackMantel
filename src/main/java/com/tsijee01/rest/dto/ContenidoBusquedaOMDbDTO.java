package com.tsijee01.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


// este deto es del tipo que te retorna la búsqueda  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenidoBusquedaOMDbDTO {
	
	
	@JsonProperty("Title")
	private String title;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("imdbID")
	private String imdbID;


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public String getImdbID() {
		return imdbID;
	}


	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	
	

}
