package com.mendes.curso.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mendes.curso.domain.Post;
import com.mendes.curso.domain.User;
import com.mendes.curso.repository.PostRepository;
import com.mendes.curso.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Thiago Teixeira Mendes", "thiagotmendes@gmail.com");
		User u2 = new User(null, "Ana Claudia", "anacg86@hotmail.com");
		User u3 = new User(null, "Joe", "joe@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post p1 = new Post(null, sdf.parse("21/03/2018") , "Partiu viagem", "Viagem para Sampa", u1);
		Post p2 = new Post(null, sdf.parse("29/03/2018") , "Partiu viagem", "Viagem para Londres", u1);
		
		postRepository.saveAll(Arrays.asList(p1, p2));
	}

}
