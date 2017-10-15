package com.tsijee01.service;

import java.util.List;

import com.tsijee01.persistence.model.Actor;

public interface ActorService {

	boolean altaActor(Actor actor);

	List<Actor> findAll();

	boolean agregarContenidoActor(Long actorId, Long contneidoId);
}
