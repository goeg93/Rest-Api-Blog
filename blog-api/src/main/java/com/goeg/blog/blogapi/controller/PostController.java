package com.goeg.blog.blogapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goeg.blog.blogapi.dto.PostDto;
import com.goeg.blog.blogapi.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	
	//Create blog post rest api	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
		
	}
	
	
	//get all post rest api
	
	@GetMapping
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> lista =  postService.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(lista,HttpStatus.OK);
	}
	
	
	// get post by Id
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
	  return  ResponseEntity.ok(postService.getPostById(id));
	}
	
	
	//Update post by id
	
	@PutMapping("/{id}")	
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){
		
		PostDto updateDto = postService.updatePost(postDto, id);
		
		  return  new ResponseEntity<PostDto>(updateDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
		  postService.deletePostById(id);
		  return  new ResponseEntity<String>("Delete success", HttpStatus.OK);
	}
	
	
}