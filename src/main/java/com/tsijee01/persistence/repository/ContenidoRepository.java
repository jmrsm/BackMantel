package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import com.tsijee01.persistence.model.Contenido;

@Repository
public interface ContenidoRepository extends BaseRepository<Contenido , Long >{

	List<Contenido> findByProveedorContenido(Long id);
	
	@EntityGraph(value="Comentario.RespondeA")
	@Override
	Optional<Contenido> findOne(Long id);

}
