package com.goeg.blog.blogapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goeg.blog.blogapi.dto.PostDto;
import com.goeg.blog.blogapi.dto.PostResponse;
import com.goeg.blog.blogapi.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}


	//Create blog post rest api
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
		
	}
	
	

	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo",defaultValue = "0",required = false) int pageNo,
			@RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir			
			){
		return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
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
