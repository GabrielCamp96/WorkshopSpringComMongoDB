package com.gabrielcamp.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcamp.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends  MongoRepository<Post, String>{

}
