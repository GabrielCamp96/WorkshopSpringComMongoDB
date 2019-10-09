package com.gabrielcamp.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielcamp.workshopmongo.domain.Comment;
import com.gabrielcamp.workshopmongo.dto.CommentDTO;
import com.gabrielcamp.workshopmongo.services.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentResource {

	@Autowired
	private CommentService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> findAll(){
		List<Comment> list = service.findAll();
		List<CommentDTO> listDto = list.stream().map(post -> new CommentDTO(post)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CommentDTO> findById(@PathVariable String id) {
		Comment obj  = service.findById(id);
		CommentDTO res = new CommentDTO(obj);
		return ResponseEntity.ok().body(res);
	}
	
	@RequestMapping(value = "/{id_user}/{id_post}" ,method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody CommentDTO post, @PathVariable String id_user, 
			@PathVariable String id_post) {
		Comment obj = service.insert(post, id_user, id_post);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/comments/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody CommentDTO comment){
		comment.setId(id);
		service.updateData(comment);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
}
