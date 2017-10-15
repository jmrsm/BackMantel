package com.tsijee01.service.bean;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Categoria;
import com.tsijee01.persistence.repository.CategoriaContenidoRepository;
import com.tsijee01.service.CategoriaContenidoService;

@Service
public class CategoriaContenidoServiceBean implements CategoriaContenidoService{

	@Autowired
	CategoriaContenidoRepository categoriaContenidoRepositoy;

	@Override
	public boolean altaCategoriaContenido(String nombreCategoria) {
		Optional<Categoria> old = categoriaContenidoRepositoy.findByNombreCategoria(nombreCategoria);
		if (old.isPresent()){
			return false;
		}else{
			Categoria cat = new Categoria();
			cat.setNombreCategoria(nombreCategoria);
			categoriaContenidoRepositoy.save(cat);
			return true;
		}
		
	}

	@Override
	public List<Categoria> obtenerCategorias() {
		return categoriaContenidoRepositoy.findAll().stream().sorted(Comparator.comparing(n->n.getNombreCategoria())).collect(Collectors.toList());
	}
	
}
