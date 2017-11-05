package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.Usuario;

@Repository
public interface HistorialContenidoRepository extends BaseRepository<HistorialContenido , Long >{

	List<HistorialContenido>  findByUsuario(Usuario usuario);

	Optional<HistorialContenido> findByContenidoAndUsuario(Contenido contenido, Usuario usuario);
	
	@EntityGraph("HistorialContenido.ConContenido")
	List<HistorialContenido> findByFavoritoAndUsuario(Boolean f,Usuario u) ;
}
