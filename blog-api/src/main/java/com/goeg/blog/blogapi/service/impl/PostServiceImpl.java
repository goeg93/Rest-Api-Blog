package com.goeg.blog.blogapi.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goeg.blog.blogapi.dto.PostDto;
import com.goeg.blog.blogapi.dto.PostResponse;
import com.goeg.blog.blogapi.entity.Post;
import com.goeg.blog.blogapi.exception.ResourceNotFoundException;
import com.goeg.blog.blogapi.repository.PostRepository;
import com.goeg.blog.blogapi.service.PostService;


@Service
public class PostServiceImpl implements PostService{

	private PostRepository postRepository;
	private ModelMapper modelMapper;
	
	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	@Override
	public PostDto createPost(PostDto post) {
		
		Post  postObj = mapDtoToEntity(post);
		Post postNew = postRepository.save(postObj);		
		PostDto postDto = mapEntityToDto(postNew); 
		return postDto;
	}

	
	//post/?pageNo=0&pageSize=4
	@Transactional(readOnly = true)
	@Override
	public PostResponse getAllPost(int pageNo, int pageSize,String sortBy, String sortDir) {
		
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
					?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		

		Pageable pageable=PageRequest.of(pageNo, pageSize,sort);
		
		Page<Post> posts=postRepository.findAll(pageable);
		
		List<Post> listOfPosts = posts.getContent();
				
		List<PostDto> content=listOfPosts.stream()
				.map(post->mapEntityToDto(post))
				.collect(Collectors.toList());
			
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}

	@Transactional(readOnly = true)
	@Override
	public PostDto getPostById(long id) {
		Post postObj = postRepository.findById(id)
									 .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		return mapEntityToDto(postObj);
	}

	@Transactional
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

	@Transactional
	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Post", "Id", id));
		postRepository.delete(post);
		
	}

	
	//Convert DTO to Entity
	
	private Post mapDtoToEntity(PostDto dto) {
		
		/*Post post = new Post();
		post.setId(dto.getId());
		post.setContent(dto.getContent());
		post.setTitle(dto.getTitle());
		post.setDescription(dto.getDescription());
		
		return post;*/
		
		Post post = this.modelMapper.map(dto, Post.class);
		return post;
	}
	
	
	private PostDto mapEntityToDto(Post post) {
		
		/*PostDto dto = new PostDto();
		dto.setId(post.getId());
		dto.setDescription(post.getDescription());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		
		*/
		
		PostDto dto = modelMapper.map(post, PostDto.class);
		return dto;
	}
	
}
