package com.goeg.blog.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goeg.blog.blogapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameOrEmail(String username, String email);
	Optional<User> findByUsername(String name);
	Boolean existsByUsername(String name);
	Boolean existsByEmail(String email);
}
