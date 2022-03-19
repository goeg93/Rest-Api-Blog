package com.goeg.blog.blogapi.dto;

import lombok.Data;

@Data
public class PostDto {
	private Long id;
	private String title;
	private String description;
	private String content;
}
