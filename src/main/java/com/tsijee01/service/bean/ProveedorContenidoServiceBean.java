package com.tsijee01.service.bean;

import java.util.List;

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
		
		if (proveedorContenidoRepository.findByNombre(nombre).isPresent()){
			return false;
		}else {
			ProveedorContenido pc = new ProveedorContenido();
			pc.setNombre(nombre);
			proveedorContenidoRepository.save(pc);
			return true;
		}
	}

	@Override
	public List<ProveedorContenido> findAll() {
		return proveedorContenidoRepository.findAll();
	}

	@Override
	public boolean actualizarProveedorContenido(ProveedorContenido proveedorContenido) {
		return true;
	}

}
