package com.tsijee01.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.rest.dto.ContenidoDTO;

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

}
