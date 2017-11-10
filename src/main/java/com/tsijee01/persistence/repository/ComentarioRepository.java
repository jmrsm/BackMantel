package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Comentario;
import com.tsijee01.persistence.model.Contenido;

@Repository
public interface ComentarioRepository extends BaseRepository<Comentario, Long>{
	
	List<Comentario> findByContenidoAndRespondeAIsNull(Contenido cont);

	@EntityGraph(value="Comentario.RespondeA")
	List<Comentario> findByContenidoAndRespondeAIsNotNull(Contenido contenido);

	
}