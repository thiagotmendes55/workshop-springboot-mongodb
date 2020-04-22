package com.mendes.curso.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
		UserDTO usuarioDTO = new UserDTO(usuario);
		
		return ResponseEntity.ok().body(usuarioDTO);		
	}
}
