package com.goeg.blog.blogapi.repository;

import org.springframework.stereotype.Repository;

import com.goeg.blog.blogapi.entity.Comment;

@Repository
public interface CommentRepository extends IGenericRepository<Comment, Long> {

}
