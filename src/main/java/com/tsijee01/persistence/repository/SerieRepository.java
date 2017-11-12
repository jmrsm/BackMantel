package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.model.Serie;

@Repository
public interface SerieRepository extends BaseRepository <Serie, Long>{

	List<Serie> findByProveedorContenido(ProveedorContenido pc);

	Page<Serie> findByEsBloqueadoFalse(Pageable pag);

}
