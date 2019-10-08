package com.gabrielcamp.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielcamp.workshopmongo.domain.Post;
import com.gabrielcamp.workshopmongo.dto.PostDTO;
import com.gabrielcamp.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> findAll(){
		List<Post> list = service.findAll();
		List<PostDTO> listDto = list.stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		Post obj  = service.findById(id);
		PostDTO res = new PostDTO(obj);
		return ResponseEntity.ok().body(res);
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@RequestBody PostDTO user) {
//		Post obj = service.insert(user);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
	
//	@RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable String id){
//		service.delete(id);
//		return ResponseEntity.ok().build();
//	}
	
//	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
//	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody PostDTO user){
//		user.setId(id);
//		service.updateData(user);
//		return ResponseEntity.ok().build();
//	}
	
}
