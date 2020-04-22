package com.mendes.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendes.curso.domain.User;
import com.mendes.curso.dto.UserDTO;
import com.mendes.curso.repository.UserRepository;
import com.mendes.curso.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> listaTodos() {
		return repo.findAll();
	}
	
	public User listaUm(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insereUsuario(User obj) {
		return repo.insert(obj);
	}
	
	public void excluiUsuario(String id) {
		this.listaUm(id);
		repo.deleteById(id);
	}
	
	public User atualizaUsuario(User obj) {
		User newObj = this.listaUm(obj.getId());
		atualizaDados(newObj, obj);
		return repo.save(newObj);
	}
	
	private void atualizaDados(User newObj, User obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());
	}
}
