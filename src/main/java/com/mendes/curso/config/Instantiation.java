package com.mendes.curso.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mendes.curso.domain.User;
import com.mendes.curso.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		User u1 = new User(null, "Thiago Teixeira Mendes", "thiagotmendes@gmail.com");
		User u2 = new User(null, "Ana Claudia", "anacg86@hotmail.com");
		User u3 = new User(null, "Joe", "joe@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}

}
