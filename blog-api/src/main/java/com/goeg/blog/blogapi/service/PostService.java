package com.goeg.blog.blogapi.service;

import java.util.List;

import com.goeg.blog.blogapi.dto.PostDto;

public interface PostService {

	PostDto createPost(PostDto post);

	List<PostDto> getAllPost();
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto dto, long id);
	
	void deletePostById(long id);
}
