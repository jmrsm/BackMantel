package com.tsijee01.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.repository.ContenidoRepository;
import com.tsijee01.service.ContenidoService;

@Service
public class ContenidoServiceBean implements ContenidoService{

	@Autowired
	ContenidoRepository contenidoRepositoy;
	
}
