package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.Usuario;

@Repository
public interface HistorialContenidoRepository extends BaseRepository<HistorialContenido , Long >{

	@EntityGraph("HistorialContenido.Full")
	List<HistorialContenido> findByUsuario(Usuario usuario);

	Optional<HistorialContenido> findByContenidoAndUsuario(Contenido contenido, Usuario usuario);
	
	@EntityGraph("HistorialContenido.ConContenido")
	List<HistorialContenido> findByFavoritoAndUsuario(Boolean f,Usuario u) ;

	Optional <HistorialContenido> findByUsuarioAndContenido(Usuario usuario, Contenido contenido);
	
	@EntityGraph("HistorialContenido.Full")
	@Query ("SELECT h FROM HistorialContenido h")
	Stream<HistorialContenido>  findAllStream();
}
