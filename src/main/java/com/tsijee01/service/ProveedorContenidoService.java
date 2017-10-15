package com.tsijee01.service;

import java.util.List;

import com.tsijee01.persistence.model.ProveedorContenido;

public interface ProveedorContenidoService {

	boolean altaProveedorContenido(String nombre);

	List<ProveedorContenido> findAll();
	
	boolean actualizarProveedorContenido(ProveedorContenido proveedorContenido);
}
