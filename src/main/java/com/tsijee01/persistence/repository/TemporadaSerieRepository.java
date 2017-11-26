package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Serie;
import com.tsijee01.persistence.model.TemporadaSerie;

@Repository
public interface TemporadaSerieRepository extends BaseRepository<TemporadaSerie , Long >{

	Optional<TemporadaSerie> findByTemporadaAndSerie(int temporada, Serie serie);
	List<TemporadaSerie> findBySerie(Serie serie);

}
