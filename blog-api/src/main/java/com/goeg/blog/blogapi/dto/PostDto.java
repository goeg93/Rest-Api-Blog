package com.goeg.blog.blogapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	private Long id;
	
	@NotEmpty
	@Size(min = 2,message = "el titulo debe tener 2 caracteres como minimo")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "la descripción debe tener 10 caracteres como minimo")
	private String description;
	
	@NotEmpty
	private String content;
}
