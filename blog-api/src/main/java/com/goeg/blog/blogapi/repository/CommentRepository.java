package com.goeg.blog.blogapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.goeg.blog.blogapi.entity.Comment;

@Repository
public interface CommentRepository extends IGenericRepository<Comment, Long> {

	List<Comment> findByPostId(long postId);
}
