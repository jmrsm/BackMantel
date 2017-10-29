//package com.tsijee01.rest.dto;
//
//import java.util.Date;
//import java.util.List;
//
//public class ContenidoFullDTO {
//
//	private long id;
//
//	private String titulo;
//
//	private String descipcion;
//
//	private Integer duracion;
//
//	private List<CategoriaDTO> categorias;
//
//	private List<DirectorDTO> directores;
//
//	private List<ActorDTO> actores;
//
//	private TipoContenidoEnum tipoContenido;
//
//	private ProveedorContenidoBasicDTO proveedorContenido;
//
//	// TODO resolver bien como hacemos con cada sub tipo, se pueden hacer DTOS
//	// que solo incluya lo referente a la sub clase
//
//	// si es evento espectaculo
//	
//	private Date eventoEspectaculoFechaInicio;
//	
//	// si es serie
//
//	private int serieTemporada;
//
//	private int serieCapitulo;
//
//	// si es evento deportivo
//
//	private String eventoDeportivoNombreEquipoVisitante;
//
//	private String eventoDeportivoNombreEquipoLocal;
//
//	private String eventoDeportivoNombreDeporte;
//
//	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getTitulo() {
//		return titulo;
//	}
//
//	public void setTitulo(String titulo) {
//		this.titulo = titulo;
//	}
//
//	public String getDescipcion() {
//		return descipcion;
//	}
//
//	public void setDescipcion(String descipcion) {
//		this.descipcion = descipcion;
//	}
//
//	public List<CategoriaDTO> getCategorias() {
//		return categorias;
//	}
//
//	public void setCategorias(List<CategoriaDTO> categorias) {
//		this.categorias = categorias;
//	}
//
//	public List<DirectorDTO> getDirectores() {
//		return directores;
//	}
//
//	public void setDirectores(List<DirectorDTO> directores) {
//		this.directores = directores;
//	}
//
//	public List<ActorDTO> getActores() {
//		return actores;
//	}
//
//	public void setActores(List<ActorDTO> actores) {
//		this.actores = actores;
//	}
//
//	public TipoContenidoEnum getTipoContenido() {
//		return tipoContenido;
//	}
//
//	public void setTipoContenido(TipoContenidoEnum tipoContenido) {
//		this.tipoContenido = tipoContenido;
//	}
//
//	public Integer getDuracion() {
//		return duracion;
//	}
//
//	public void setDuracion(Integer duracion) {
//		this.duracion = duracion;
//	}
//
//	public ProveedorContenidoBasicDTO getProveedorContenido() {
//		return proveedorContenido;
//	}
//
//	public void setProveedorContenido(ProveedorContenidoBasicDTO proveedorContenido) {
//		this.proveedorContenido = proveedorContenido;
//	}
//
//	public int getSerieTemporada() {
//		return serieTemporada;
//	}
//
//	public void setSerieTemporada(int serieTemporada) {
//		this.serieTemporada = serieTemporada;
//	}
//
//	public int getSerieCapitulo() {
//		return serieCapitulo;
//	}
//
//	public void setSerieCapitulo(int serieCapitulo) {
//		this.serieCapitulo = serieCapitulo;
//	}
//
//	public String getEventoDeportivoNombreEquipoVisitante() {
//		return eventoDeportivoNombreEquipoVisitante;
//	}
//
//	public void setEventoDeportivoNombreEquipoVisitante(String eventoDeportivoNombreEquipoVisitante) {
//		this.eventoDeportivoNombreEquipoVisitante = eventoDeportivoNombreEquipoVisitante;
//	}
//
//	public String getEventoDeportivoNombreEquipoLocal() {
//		return eventoDeportivoNombreEquipoLocal;
//	}
//
//	public void setEventoDeportivoNombreEquipoLocal(String eventoDeportivoNombreEquipoLocal) {
//		this.eventoDeportivoNombreEquipoLocal = eventoDeportivoNombreEquipoLocal;
//	}
//
//	public String getEventoDeportivoNombreDeporte() {
//		return eventoDeportivoNombreDeporte;
//	}
//
//	public void setEventoDeportivoNombreDeporte(String eventoDeportivoNombreDeporte) {
//		this.eventoDeportivoNombreDeporte = eventoDeportivoNombreDeporte;
//	}
//
//	public Date getEventoEspectaculoFechaInicio() {
//		return eventoEspectaculoFechaInicio;
//	}
//
//	public void setEventoEspectaculoFechaInicio(Date eventoEspectaculoFechaInicio) {
//		this.eventoEspectaculoFechaInicio = eventoEspectaculoFechaInicio;
//	}
//	
//}
