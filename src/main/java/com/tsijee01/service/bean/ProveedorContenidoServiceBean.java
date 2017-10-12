package com.tsijee01.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.repository.ProveedorContenidoRepository;
import com.tsijee01.service.ProveedorContenidoService;

@Service
public class ProveedorContenidoServiceBean implements ProveedorContenidoService {

	@Autowired 
	private ProveedorContenidoRepository proveedorContenidoRepository;
	
	@Override
	public boolean altaProveedorContenido(String nombre) {
		
		if (proveedorContenidoRepository.findByName(nombre).isPresent()){
			return false;
		}else {
			ProveedorContenido pc = new ProveedorContenido();
			pc.setNombre(nombre);
			proveedorContenidoRepository.save(pc);
			return true;
		}
		
	}

}
