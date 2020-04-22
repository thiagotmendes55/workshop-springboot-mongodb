package com.mendes.curso.services;

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
		return repo.findByTituloContainingIgnoreCase(texto);
	}
}
