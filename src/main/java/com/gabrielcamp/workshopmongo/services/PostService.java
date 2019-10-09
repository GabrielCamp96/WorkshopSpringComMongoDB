package com.gabrielcamp.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcamp.workshopmongo.domain.Post;
import com.gabrielcamp.workshopmongo.domain.User;
import com.gabrielcamp.workshopmongo.dto.AuthorDTO;
import com.gabrielcamp.workshopmongo.dto.PostDTO;
import com.gabrielcamp.workshopmongo.repository.PostRepository;
import com.gabrielcamp.workshopmongo.repository.UserRepository;
import com.gabrielcamp.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Post> findAll() {
		return repo.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado no Banco de Dados."));
	}
	
	public Post insert(PostDTO obj, String id_user) {
		Post res = FromDTO(obj);
		User user = userRepository.findById(id_user).get();
		AuthorDTO author = new AuthorDTO(user);
		res.setAuthor(author);

		res = repo.save(res);
		
		user.getPosts().add(res);
		userRepository.save(user);
		
		return res;
	}
	
	public void delete(String id) {
		Post post = findById(id);
		repo.delete(post);
	}

	public static Post FromDTO(PostDTO obj) {
		Post res = new Post();
		res.setTitle(obj.getTitle());
		res.setDate(obj.getDate());
		res.setBody(obj.getBody());
		return res;
	}
	
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
	
}
