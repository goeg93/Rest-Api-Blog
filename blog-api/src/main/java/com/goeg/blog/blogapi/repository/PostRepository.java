package com.goeg.blog.blogapi.repository;

import org.springframework.stereotype.Repository;

import com.goeg.blog.blogapi.entity.Post;

@Repository
public interface PostRepository extends IGenericRepository<Post, Long>{

}
