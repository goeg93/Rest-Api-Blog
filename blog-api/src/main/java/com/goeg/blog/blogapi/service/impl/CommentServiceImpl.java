package com.goeg.blog.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.goeg.blog.blogapi.dto.CommentDto;
import com.goeg.blog.blogapi.entity.Comment;
import com.goeg.blog.blogapi.entity.Post;
import com.goeg.blog.blogapi.exception.ResourceNotFoundException;
import com.goeg.blog.blogapi.repository.CommentRepository;
import com.goeg.blog.blogapi.repository.PostRepository;
import com.goeg.blog.blogapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository commentRepository;
	private ModelMapper modelMapper;
	private PostRepository postRepository;
	
	


	public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper,
			PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
		this.postRepository = postRepository;
	}


	@Override
	public CommentDto createComment(Long postId, CommentDto comemtDto) {
		Comment comment = mapDtoToEntity(comemtDto);
		
		Post post = this.postRepository.findById(postId)
				                       .orElseThrow(
				                    		   () -> new ResourceNotFoundException("Post", "id", postId)
				                    		   );
		comment.setPost(post);
		
		Comment commentNew = this.commentRepository.save(comment);
		
		return mapEntityToDto(commentNew);
		
	}


	@Override
	public List<CommentDto> getCommentsByPostId(Long postId) {
	
		List<Comment> comments = this.commentRepository.findByPostId(postId);		
		return comments.stream().map(x -> mapEntityToDto(x)).collect(Collectors.toList());
	}


	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CommentDto updateCommnet(Long commnetId, Long commnentId, CommentDto comment) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteComment(Long commentId, Long PostId) {
		// TODO Auto-generated method stub
		
	}

	
	
	public CommentDto mapEntityToDto(Comment comment) {
		CommentDto dto = this.modelMapper.map(comment, CommentDto.class);
		return dto;
	}

	
	public Comment mapDtoToEntity(CommentDto dto) {
		Comment comment = this.modelMapper.map(dto, Comment.class);
		return comment;
	}
	
	
}
