package com.gabrielcamp.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielcamp.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends  MongoRepository<Post, String>{

	@Transactional(readOnly = true)
	List<Post> findByTitleIsContaining(String title);
	
}
