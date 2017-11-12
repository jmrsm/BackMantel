package com.tsijee01.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Actor;
import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.model.Director;
import com.tsijee01.persistence.model.Pelicula;
import com.tsijee01.persistence.model.ProveedorContenido;

@Repository
public interface PeliculRepository extends BaseRepository<Pelicula, Long>{

	Page<Pelicula> findByTituloContaining(Pageable p,String busqueda );

	List<Pelicula> findByTituloLike(String query);

	Page<Pelicula> findByCategorias(Pageable p, Categoria categoria);

	Page<Pelicula> findByDirectores(Pageable pag, Director director);

	Page<Pelicula> findByActores(Pageable pag, Actor actor);

	List<Pelicula>  findByProveedorContenido(ProveedorContenido pc);

	Page<Pelicula> findByEsBloqueadoFalse(Pageable pag);
	
}
