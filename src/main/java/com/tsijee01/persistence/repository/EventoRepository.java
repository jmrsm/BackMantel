package com.tsijee01.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Evento;

@Repository
public interface EventoRepository extends BaseRepository <Evento, Long>{

	Page<Evento> findByTituloContainingOrDescipcionContaining(Pageable pag, String query, String query2);

}
