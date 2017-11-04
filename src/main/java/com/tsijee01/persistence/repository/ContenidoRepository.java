package com.tsijee01.persistence.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Contenido;

@Repository
public interface ContenidoRepository extends BaseRepository<Contenido , Long >{

	List<Contenido> findByProveedorContenido(Long id);
}
