package com.tsijee01.service;

import java.util.List;

import com.tsijee01.persistence.model.Comentario;

public interface ComentarioService {

	void comentarComentario(Long contenidoId, String comentario, Long comentarioPadreId, Long usuarioId);

	List<Comentario> listarComentarios(Long contenidoId);

}
