package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.Usuario;

@Repository
public interface HistorialContenidoRepository extends BaseRepository<HistorialContenido , Long >{

	List<HistorialContenido>  findByUsuario(Usuario usuario);

	Optional<HistorialContenido> findByContenidoAndUsuario(Contenido contenido, Usuario usuario);

	static List<HistorialContenido> findByFavoritoTrueAndUsuario(Optional<Usuario> u) {
		// TODO Auto-generated method stub
		return null;
	}
}
