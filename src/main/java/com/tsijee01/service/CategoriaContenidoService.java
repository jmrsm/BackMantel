package com.tsijee01.service;

import java.util.List;

import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.rest.dto.CategoriaDTO;

public interface CategoriaContenidoService {

	boolean altaCategoriaContenido(String nombreCategoria);

	List<Categoria> obtenerCategorias();

}
