package com.tsijee01.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.Actor;

@Repository
public interface ActorRepository extends BaseRepository<Actor, Long> {

	Optional<Actor> findByNombreCompleto(String nombreCompleto);

}
