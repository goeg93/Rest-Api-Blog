package com.goeg.blog.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.goeg.blog.blogapi.dto.PostDto;
import com.goeg.blog.blogapi.entity.Post;
import com.goeg.blog.blogapi.exception.ResourceNotFoundException;
import com.goeg.blog.blogapi.repository.PostRepository;
import com.goeg.blog.blogapi.service.PostService;


@Service
public class PostServiceImpl implements PostService{

	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDto createPost(PostDto post) {
		
		Post  postObj = mapDtoToEntity(post);
		Post postNew = postRepository.save(postObj);		
		PostDto postDto = mapEntityToDto(postNew); 
		return postDto;
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> list = postRepository.findAll();
		List<PostDto> listDto = list.stream()
									.map(post ->  mapEntityToDto(post))
									.collect(Collectors.toList());
		return listDto;
	}

	@Override
	public PostDto getPostById(long id) {
		Post postObj = postRepository.findById(id)
									 .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		return mapEntityToDto(postObj);
	}

	@Override
	public PostDto updatePost(PostDto dto, long id) {
		Post post = postRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Post", "Id", id));
		post.setContent(dto.getContent());
		post.setDescription(dto.getDescription());
		post.setTitle(dto.getTitle());
		Post postUpdate = postRepository.save(post);
		return  mapEntityToDto(postUpdate);
	}

	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Post", "Id", id));
		postRepository.delete(post);
		
	}

	
	//Convert DTO to Entity
	
	private Post mapDtoToEntity(PostDto dto) {
		
		Post post = new Post();
		post.setId(dto.getId());
		post.setContent(dto.getContent());
		post.setTitle(dto.getTitle());
		post.setDescription(dto.getDescription());
		
		return post;
		
	}
	
	
	private PostDto mapEntityToDto(Post post) {
		
		PostDto dto = new PostDto();
		dto.setId(post.getId());
		dto.setDescription(post.getDescription());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		
		return dto;
		
	}
	
}
