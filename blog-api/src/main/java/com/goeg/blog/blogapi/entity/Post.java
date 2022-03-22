package com.goeg.blog.blogapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 2, message = "el titulo debe tener 2 caracteres como minimo")
	@Column(name = "title", nullable = false)
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "la descripción debe tener 10 caracteres como minimo")
	@Column(name = "description", nullable = false)
	private String description;
	
	@NotEmpty
	@Column(name = "content", nullable = false)
	private String content;
	
	
}
