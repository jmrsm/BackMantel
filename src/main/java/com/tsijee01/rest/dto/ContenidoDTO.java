package com.tsijee01.rest.dto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// este deto es lo que te retorna completo para guardar los datos en la basa una vez que tenemos el id 
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenidoDTO {

	private long id;

	private List<ComentarioDTO> comentarios;

	private List<ContenidoDTO> similares;

	private ProveedorContenidoDTO proveedorContenido;

	private TipoContenidoEnum tipoContenido;

	// si es evento
	private Boolean esPago;
	
	private int precio;
	
	private Date fechaInicio;
	
	
	// si es evento deportivo
	private String eventoDeportivoNombreEquipoVisitante;

	private String eventoDeportivoNombreEquipoLocal;

	private String eventoDeportivoNombreDeporte;

	@JsonProperty("Title")
	private String titulo;

	@JsonProperty("Year")
	private Integer anio;

	// @JsonProperty("Rated")
	// private String rated;

	@JsonProperty("Released")
	// @JsonFormat(pattern="dd MMM yyyy")
	private Date fechaPublicado;

	@JsonProperty("Runtime")
	private int duracion;

	@JsonProperty("Genre")
	private List<CategoriaDTO> categorias;

	@JsonProperty("Director")
	private List<DirectorDTO> directores;

	// @JsonProperty("Writer")
	// private String writer;

	@JsonProperty("Actors")
	private List<ActorDTO> actores;

	@JsonProperty("Plot")
	private String descipcion;

	@JsonProperty("Lenguage")
	private String lenguage;

	@JsonProperty("Country")
	private String country;

	// @JsonProperty("Awards")
	// private List<String> awards;

	@JsonProperty("Poster")
	private String fotoPortada;

	@JsonProperty("imdbRating")
	private BigDecimal ranking;

	@JsonProperty("imdbVotes")
	private int cantVotos;

	@JsonProperty("imdbID")
	private String imdbID;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Website")
	private String website;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Boolean getEsPago() {
		return esPago;
	}

	public void setEsPago(Boolean esPago) {
		this.esPago = esPago;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getEventoDeportivoNombreEquipoVisitante() {
		return eventoDeportivoNombreEquipoVisitante;
	}

	public void setEventoDeportivoNombreEquipoVisitante(String eventoDeportivoNombreEquipoVisitante) {
		this.eventoDeportivoNombreEquipoVisitante = eventoDeportivoNombreEquipoVisitante;
	}

	public String getEventoDeportivoNombreEquipoLocal() {
		return eventoDeportivoNombreEquipoLocal;
	}

	public void setEventoDeportivoNombreEquipoLocal(String eventoDeportivoNombreEquipoLocal) {
		this.eventoDeportivoNombreEquipoLocal = eventoDeportivoNombreEquipoLocal;
	}

	public String getEventoDeportivoNombreDeporte() {
		return eventoDeportivoNombreDeporte;
	}

	public void setEventoDeportivoNombreDeporte(String eventoDeportivoNombreDeporte) {
		this.eventoDeportivoNombreDeporte = eventoDeportivoNombreDeporte;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public void setAnio(String anio) {
		this.anio = Integer.valueOf(anio.substring(0, 3));
	}

	public void setDirectores(List<DirectorDTO> director) {
		this.directores = director;
	}

	public void setActores(List<ActorDTO> actores) {
		this.actores = actores;
	}

	public Date getFechaPublicado() {
		return fechaPublicado;
	}

	public void setFechaPublicado(Date fechaPublicado) {
		this.fechaPublicado = fechaPublicado;
	}

	public void setFechaPublicado(String released) throws ParseException {
		this.fechaPublicado = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).parse(released);
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = Integer.valueOf(duracion.split(" ")[0]);
	}

	public List<CategoriaDTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = Arrays.asList(categorias.split(",")).stream().map(CategoriaDTO::new)
				.collect(Collectors.toList());
	}

	public void setCategorias(List<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public List<ActorDTO> getActores() {
		return actores;
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

	public BigDecimal getRanking() {
		return ranking;
	}

	public void setRanking(BigDecimal ranking) {
		this.ranking = ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = new BigDecimal(ranking);

	}

	public String getFotoPortada() {
		return fotoPortada;
	}

	public void setFotoPortada(String fotoPortada) {
		this.fotoPortada = fotoPortada;
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

	public List<DirectorDTO> getDirectores() {
		return directores;
	}

	public void setDirectores(String director) {
		this.directores = Arrays.asList(director.split(",")).stream().map(DirectorDTO::new)
				.collect(Collectors.toList());
	}

	public void setActores(String actor) {
		this.actores = Arrays.asList(actor.split(",")).stream().map(ActorDTO::new).collect(Collectors.toList());

	}

	public int getCantVotos() {
		return cantVotos;
	}

	public void setCantVotos(String cantVotos) {
		this.cantVotos = Integer.valueOf(cantVotos.replace(",", ""));
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}

	public List<ContenidoDTO> getSimilares() {
		return similares;
	}

	public void setSimilares(List<ContenidoDTO> similares) {
		this.similares = similares;
	}

	public ProveedorContenidoDTO getProveedorContenido() {
		return proveedorContenido;
	}

	public void setProveedorContenido(ProveedorContenidoDTO proveedorContenido) {
		this.proveedorContenido = proveedorContenido;
	}

	public TipoContenidoEnum getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(TipoContenidoEnum tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setCantVotos(int cantVotos) {
		this.cantVotos = cantVotos;
	}

}
