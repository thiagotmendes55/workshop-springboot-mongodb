package com.mendes.curso.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendes.curso.domain.Post;
import com.mendes.curso.repository.PostRepository;
import com.mendes.curso.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post listaUm(String id) {
		Optional<Post> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> encontrarPorTitulo(String texto) {
		return repo.procuraPorTituloManual(texto);
	}
	
	public List<Post> encontrarCompleto(String texto, Date dtInicio, Date dtFim) {
		dtFim = new Date(dtFim.getTime() + 24 * 60 * 60 * 1000);
		
		return repo.procuraPostCompleto(texto, dtInicio, dtFim);
	}
}
