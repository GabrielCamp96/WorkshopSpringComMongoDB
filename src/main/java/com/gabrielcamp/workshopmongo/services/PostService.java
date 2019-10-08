package com.gabrielcamp.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcamp.workshopmongo.domain.Post;
import com.gabrielcamp.workshopmongo.repository.PostRepository;
import com.gabrielcamp.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository repo;
	
	public List<Post> findAll() {
		return repo.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado no Banco de Dados."));
	}
	
//	public Post insert(PostDTO obj) {
//		Post res = FromDTO(obj);
//		res = repo.save(res);
//		return res;
//	}
	
//	public void delete(String id) {
//		Post user = findById(id);
//		repo.delete(user);
//	}
	
//	public Post update(PostDTO obj) {
//		Post user = findById(obj.getId());
//		user = repo.save(user);
//		return user;
//	}
	
//	public Post updateData(PostDTO obj) {
//		Post user = repo.findById(obj.getId()).get();
//		user.setName(obj.getName() != null? obj.getName() : user.getName());
//		user.setEmail(obj.getEmail() != null? obj.getEmail() : user.getEmail());
//		return repo.save(user);
//	}

//	public static Post FromDTO(PostDTO obj) {
//		Post resp = new Post();
//		
//		resp.setId(obj.getId());
//		resp.setName(obj.getName());
//		resp.setEmail(obj.getEmail());
//		
//		return resp;
//	}
	
}
