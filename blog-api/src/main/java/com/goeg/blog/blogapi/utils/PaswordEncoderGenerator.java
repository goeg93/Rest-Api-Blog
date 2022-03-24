package com.goeg.blog.blogapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PaswordEncoderGenerator {
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("admin"));
	}
}
