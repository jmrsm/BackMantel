package com.tsijee01.service;

import java.util.List;

import com.tsijee01.persistence.model.Director;

public interface DirectorService {

	boolean altaDirector(Director director);

	List<Director> findAll();

	boolean agregarContenidoDirector(Long directorId, Long contenidoId);
}
