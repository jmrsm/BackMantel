package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.Usuario;

@Repository
public interface HistorialContenidoRepository extends BaseRepository<HistorialContenido , Long >{

	List<HistorialContenido>  findByUsuario(Usuario usuario);
}
