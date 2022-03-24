package com.goeg.blog.blogapi.repository;

import java.util.List;
import com.goeg.blog.blogapi.entity.Comment;

public interface CommentRepository extends IGenericRepository<Comment, Long> {

	List<Comment> findByPostId(long postId);
}
