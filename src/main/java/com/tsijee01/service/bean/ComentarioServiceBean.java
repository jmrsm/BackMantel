package com.tsijee01.service.bean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Comentario;
import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.ComentarioRepository;
import com.tsijee01.persistence.repository.ContenidoRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.ComentarioService;

@Service
public class ComentarioServiceBean implements ComentarioService {

	@Autowired
	ContenidoRepository contenidoRepository;

	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Autowired 
	UsuarioRepository usuarioRepository;
	@Override
	public void comentarComentario(Long contenidoId, String comentario, Long comentarioPadreId, Long usuarioId) {
		Optional<Contenido> cont = contenidoRepository.findOne(contenidoId);
		Comentario coment = new Comentario();
		Optional<Comentario> cp = comentarioRepository.findOne(comentarioPadreId);
		Optional<Usuario> u = usuarioRepository.findOne(usuarioId);
//		cp.get().set
		
		if (cont.isPresent() && cp.isPresent()) {
			coment.setContenidoComentario(comentario);
			coment.setContenido(cont.get());
			coment.setUsuario(u.get());
			coment.setRespondeA(cp.get());
			
		}
		else 
		{
			
		}
		
		comentarioRepository.save(coment).getId();
		
	}
	@Override
	public List<Comentario> listarComentarios(Long contenidoId) {

		Optional<Contenido> cont = contenidoRepository.findOne(contenidoId);
		
		List<Comentario> lc = comentarioRepository.findByContenidoAndRespondeAIsNull(cont.get());
		List<Comentario> lr = comentarioRepository.findByContenidoAndRespondeAIsNotNull(cont.get());
		
		for (Comentario c:lc) {
			c.setRespuestas(lr.stream().filter(x->x.getRespondeA().getId()==c.getId()).collect(Collectors.toList()));
			
		}
		
		
		return lc;
	}

}
