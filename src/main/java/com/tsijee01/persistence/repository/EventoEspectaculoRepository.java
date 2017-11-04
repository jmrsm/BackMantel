package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.EventoEspectaculo;
import com.tsijee01.persistence.model.ProveedorContenido;

@Repository
public interface EventoEspectaculoRepository extends BaseRepository <EventoEspectaculo, Long>{

	List<EventoEspectaculo> findByProveedorContenido(ProveedorContenido pc);

}
