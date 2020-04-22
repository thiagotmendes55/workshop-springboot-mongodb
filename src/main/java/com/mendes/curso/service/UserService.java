package com.mendes.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendes.curso.domain.User;
import com.mendes.curso.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> listaTodos() {
		return repo.findAll();
	}
}
