package com.goeg.blog.blogapi.service;

import com.goeg.blog.blogapi.dto.PostDto;
import com.goeg.blog.blogapi.dto.PostResponse;

public interface PostService {

	PostDto createPost(PostDto post);

	 PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto dto, long id);
	
	void deletePostById(long id);
}
