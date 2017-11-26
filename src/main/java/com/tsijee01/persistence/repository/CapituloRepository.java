package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.CapituloSerie;
import com.tsijee01.persistence.model.TemporadaSerie;

@Repository
public interface CapituloRepository extends BaseRepository<CapituloSerie , Long >{

	List<CapituloSerie> findByTemporada(TemporadaSerie temporada);
}
