package com.goeg.blog.blogapi.dto;

import lombok.Data;

@Data
public class JWTAuthResponse {

	private String accessToken;
	private String tokenType="Bearer";
	
	
	public JWTAuthResponse(String accessToken) {
		this.accessToken=accessToken;
	}
}
