package com.tsijee01.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.Serie;
import com.tsijee01.rest.dto.ContenidoFullDTO;

public interface ContenidoService {

	void altaContenidoMultimedia(Long contenidoId, MultipartFile contenido) throws IOException;
	
	boolean altaContenido(ContenidoFullDTO contenido);

	boolean altaPelicula(Pelicula contenido);
	
	boolean altaSerie(Serie contenido, int temporada, int capitulo);

}
