package com.tsijee01.service.bean;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Actor;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.repository.ActorRepository;
import com.tsijee01.service.ActorService;

@Service
public class ActorServiceBean  implements ActorService{

	@Autowired
	private ActorRepository actorRepository;
	
	@Override
	public boolean altaActor(Actor actor) {
		Optional<Actor> old = actorRepository.findByNombreCompleto(actor.getNombreCompleto());
		if (old.isPresent()){
			return false;
		}else{
			actorRepository.save(actor);
			return true;
		}
	}

	@Override
	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	@Override
	public boolean agregarContenidoActor(Long actorId, Long contneidoId) {
		return false;
	}

		

}
