package com.goeg.blog.blogapi.service;

import java.util.List;

public interface CrudService<T,Id> {

	T create(T t);
	
	List<T> getAll();
	
	T getById(Id id);
	
	T update(T t);
	
	void delete(Id id);
}
