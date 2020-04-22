package com.mendes.curso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mendes.curso.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}