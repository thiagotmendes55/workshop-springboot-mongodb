package com.mendes.curso.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.curso.domain.Post;
import com.mendes.curso.resources.util.URL;
import com.mendes.curso.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> listaUm(@PathVariable String id) {
		Post post = service.listaUm(id);
		return ResponseEntity.ok().body(post);		
	}
	
	@RequestMapping(value="/titulo", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> encontrarPorTitulo(@RequestParam(value="texto", defaultValue="") String texto) {
		texto = URL.decodeParam(texto);
		
		List<Post> posts = service.encontrarPorTitulo(texto);
		
		return ResponseEntity.ok().body(posts);		
	}
	
	@RequestMapping(value="/pesquisa", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> pesquisaTotal(
			@RequestParam(value="texto", defaultValue="") String texto,
			@RequestParam(value="dtInicio", defaultValue="") String dtInicio,
			@RequestParam(value="dtFim", defaultValue="") String dtFim) {
		texto = URL.decodeParam(texto);
		Date dtI = URL.convertDate(dtInicio, new Date(0L));
		Date dtF = URL.convertDate(dtFim, new Date());
		
		List<Post> posts = service.encontrarCompleto(texto, dtI, dtF);
		
		return ResponseEntity.ok().body(posts);		
	}
}
