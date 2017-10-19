package com.tsijee01.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


// este deto es lo que te retorna completo para guardar los datos en la basa una vez que tenemos el id 
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenidoOMDbDTO {

	private String title;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Rated")
	private String rated;

	@JsonProperty("Released")
	@JsonFormat(pattern="dd MMM yyyy")
	private Date released;

	@JsonProperty("Runtime")
	private String runtime;

	@JsonProperty("Genre")
	private String genre;

	@JsonProperty("Director")
	private String director;

	@JsonProperty("Writer")
	private String writer;

//	@JsonProperty("Actors")
//	private List<String> actors;

	@JsonProperty("plot")
	private String plot;

	@JsonProperty("Lenguage")
	private String lenguage;

	@JsonProperty("Countr")
	private String country;

//	@JsonProperty("Awards")
//	private List<String> awards;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("imdbRating")
	private String imdbRating;

	@JsonProperty("imdbID")
	private String imdbID;
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("Website")
	private String website;

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

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLenguage() {
		return lenguage;
	}

	public void setLenguage(String lenguage) {
		this.lenguage = lenguage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
