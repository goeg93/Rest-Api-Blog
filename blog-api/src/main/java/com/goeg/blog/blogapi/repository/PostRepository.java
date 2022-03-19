package com.goeg.blog.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goeg.blog.blogapi.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
