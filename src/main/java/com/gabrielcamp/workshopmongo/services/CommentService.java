package com.gabrielcamp.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcamp.workshopmongo.domain.Comment;
import com.gabrielcamp.workshopmongo.domain.Post;
import com.gabrielcamp.workshopmongo.domain.User;
import com.gabrielcamp.workshopmongo.dto.AuthorDTO;
import com.gabrielcamp.workshopmongo.dto.CommentDTO;
import com.gabrielcamp.workshopmongo.repository.CommentRepository;
import com.gabrielcamp.workshopmongo.repository.PostRepository;
import com.gabrielcamp.workshopmongo.repository.UserRepository;
import com.gabrielcamp.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class CommentService {

	@Autowired
	private CommentRepository repo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Comment> findAll() {
		return repo.findAll();
	}
	
	public Comment findById(String id) {
		Optional<Comment> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado no Banco de Dados."));
	}
	
	public Comment insert(CommentDTO obj, String id_user, String id_post) {
		Comment res = FromDTO(obj);
		User user = userRepository.findById(id_user).get();
		Post post = postRepository.findById(id_post).get();
		
		AuthorDTO author = new AuthorDTO(user);
		res.setAuthor(author);
		post.getComments().add(res);
		
		res = repo.save(res);
		postRepository.save(post);
		
		return res;
	}
	
//	public void delete(String id) {
//		Comment post = findById(id);
//		repo.delete(post);
//	}

//	public Comment updateData(CommentDTO obj) {
//		Comment post = repo.findById(obj.getId()).get();
//		post.setDate(obj.getTitle() != null || obj.getBody() != null ? 
//				Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()) : post.getDate());
//		post.setTitle(obj.getTitle() != null? obj.getTitle() : post.getTitle());
//		post.setBody(obj.getBody() != null? obj.getBody() : post.getBody());
//		
//		return repo.save(post);
//	}

	public static Comment FromDTO(CommentDTO obj) {
		Comment res = new Comment();
		res.setDate(obj.getDate());
		res.setText(obj.getText());
		return res;
	}
	
	
}
