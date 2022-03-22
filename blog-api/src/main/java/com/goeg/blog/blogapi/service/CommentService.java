package com.goeg.blog.blogapi.service;

import java.util.List;

import com.goeg.blog.blogapi.dto.CommentDto;

public interface CommentService {

		CommentDto createComment(Long postId,CommentDto comemt);
		
		List<CommentDto> getCommentsByPostId(Long postId);
		
		CommentDto getCommentById(Long postId, Long commentId);
		
		CommentDto  updateCommnet(Long commnetId, Long commnentId, CommentDto comment);
		
		void deleteComment(Long commentId, Long PostId);
}
