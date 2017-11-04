package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.EventoDeportivo;
import com.tsijee01.persistence.model.ProveedorContenido;

@Repository
public interface EventoDeportivoRepository extends BaseRepository<EventoDeportivo, Long>{

	List<EventoDeportivo> findByProveedorContenido(ProveedorContenido pc);

}
