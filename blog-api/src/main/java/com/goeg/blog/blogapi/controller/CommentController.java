package com.goeg.blog.blogapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goeg.blog.blogapi.dto.CommentDto;
import com.goeg.blog.blogapi.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(name="postId") Long postId){
		return commentService.getCommentsByPostId(postId);
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(name="postId")Long postId, @RequestBody CommentDto comment){
		return new ResponseEntity<CommentDto>(this.commentService.createComment(postId, comment),HttpStatus.CREATED);
		
	}
}
