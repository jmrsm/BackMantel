package com.tsijee01.service.bean;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Director;
import com.tsijee01.persistence.repository.DirectorRepository;
import com.tsijee01.service.DirectorService;

@Service
public class DirectorServiceBean  implements DirectorService{

	@Autowired
	private DirectorRepository directorRepository;
	
	@Override
	public boolean altaDirector(Director director) {
		Optional<Director> old = directorRepository.findByNombreCompleto(director.getNombreCompleto());
		if (old.isPresent()){
			return false;
		}else{
			directorRepository.save(director);
			return true;
		}
	}

	@Override
	public List<Director> findAll() {
		return directorRepository.findAll();
	}

	@Override
	public boolean agregarContenidoDirector(Long directorId, Long contneidoId) {
		return false;
	}

		

}
