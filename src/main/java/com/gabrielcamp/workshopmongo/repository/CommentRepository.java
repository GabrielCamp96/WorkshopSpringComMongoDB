package com.gabrielcamp.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcamp.workshopmongo.domain.Comment;

@Repository
public interface CommentRepository extends  MongoRepository<Comment, String>{

}
