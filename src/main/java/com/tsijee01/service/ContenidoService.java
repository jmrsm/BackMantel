package com.tsijee01.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.tsijee01.persistence.model.CapituloSerie;
import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Evento;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.persistence.model.TemporadaSerie;
import com.tsijee01.rest.dto.ContenidoDTO;
import com.tsijee01.rest.dto.EpisodioDTO;

public interface ContenidoService {

	void altaContenidoMultimedia(Long contenidoId, MultipartFile contenido) throws IOException;

	boolean altaContenido(ContenidoDTO contenido);

	Long  altaPelicula(Pelicula contenido, Long proveedorContenidoId, String path, Boolean esDestacado);

	Long altaSerie(Serie contenido, Long proveedorContenidoId, Boolean esDestacado);

	Page<Pelicula> buscarPelicula(Pageable pag, String query);

	List<Categoria> listarCategorias();

	Page<Pelicula> buscarPeliculaPorGenero(Pageable pag, Long generoId);

	Page<Pelicula> buscarPeliculaPorActor(Pageable pag, Long actorId);

	Page<Pelicula> buscarPeliculaPorDirector(Pageable pag, Long directorId);

	List<Categoria> obtenerTiposContenido();
	
	List<Contenido> obtenermicontenido(String email);
	
	void marcarDestacado(Long contenidoId, Boolean esDestacado);

	void guardarReproduccion(Long idUsuario, Long idContenido, Long tiempo);

	void marcarFavorito(Long contenidoId, Boolean esFavorito, Long usuarioId);

	List<ContenidoDTO> listarContenidoProveedor(String email);

	Long verContenido(Long usuarioId, Long contenidoId);
	
	Page<Pelicula> buscarTodasLasPeliculas(Pageable pag);

	Long altaEpisodio(Long serieId, String path, int episodio, int temporada);

	void comentarContenido(Long contenidoId, String comentario, Long usuarioId);

	void marcarSpoiler(Long comentarioId, Long usuarioId);

	void valorarContenido(Long contenidoId, int puntaje, Long usuarioId);

	boolean comprarContenido(Long idContenido, String emailUsuario);

	boolean verificarPagoContenido(Long idContenido, String emailUsuario);

	Page<Serie> buscarTodasLasSeries(Pageable pag);

	Page<Evento> listarEventos(Pageable pag);

	Page<Evento> listarEventosConBusqueda(Pageable pag, String query);

	Page<Pelicula> listarPeliculas(Pageable pag);

	Page<Serie> buscarSerie(Pageable pag, String query);

	Page<Serie> listarSeries(Pageable pag);
	
	Contenido verDatoContenido(Long idContenido);

	void agregarPrecio(Long id, int precio);
	
	List<CapituloSerie> obtenerEpisodiosSeries(Long idSerie);
	
	TemporadaSerie findTemporada(long id);

	List<Contenido> recomendarContenido(String email);

}
