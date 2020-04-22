package com.mendes.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mendes.curso.domain.Post;
import com.mendes.curso.domain.User;
import com.mendes.curso.dto.UserDTO;
import com.mendes.curso.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> listaTodos() {
		List<User> usuarios = service.listaTodos();
		List<UserDTO> usuariosDTO = usuarios.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(usuariosDTO);		
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> listaUm(@PathVariable String id) {
		User usuario = service.listaUm(id);
		return ResponseEntity.ok().body(new UserDTO(usuario));		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insere(@RequestBody UserDTO objDTO) {
		User usuario = service.fromDTO(objDTO);
		usuario = service.insereUsuario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<UserDTO> exclui(@PathVariable String id) {
		service.excluiUsuario(id);
		return ResponseEntity.noContent().build();		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualiza(@PathVariable String id, @RequestBody UserDTO objDTO) {
		User usuario = service.fromDTO(objDTO);
		usuario.setId(id);
		usuario = service.atualizaUsuario(usuario);
		
		return ResponseEntity.noContent().build();		
	}
	
	@RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> listaPosts(@PathVariable String id) {
		User usuario = service.listaUm(id);
		return ResponseEntity.ok().body(usuario.getPosts());		
	}
}
