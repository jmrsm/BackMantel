package com.tsijee01.rest.dto;

import java.util.Date;
import java.util.List;

public class ContenidoOMDbDTO {

	private String Title;

	private int year;

	private String Rated;

	private Date Released;

	private String Runtime;

	private String Genre;

	private String Director;

	private String Writer;

	private List<String> Actors;

	private String Plot;

	private String Lenguage;

	private String Country;

	private List<String> Awards;

	private String Poster;

	private String imdbRating;

	private String imdbID;

	private String Type;

	private String Website;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRated() {
		return Rated;
	}

	public void setRated(String rated) {
		Rated = rated;
	}

	public Date getReleased() {
		return Released;
	}

	public void setReleased(Date released) {
		Released = released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String runtime) {
		Runtime = runtime;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String writer) {
		Writer = writer;
	}

	public List<String> getActors() {
		return Actors;
	}

	public void setActors(List<String> actors) {
		Actors = actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}

	public String getLenguage() {
		return Lenguage;
	}

	public void setLenguage(String lenguage) {
		Lenguage = lenguage;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public List<String> getAwards() {
		return Awards;
	}

	public void setAwards(List<String> awards) {
		Awards = awards;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
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
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

}
