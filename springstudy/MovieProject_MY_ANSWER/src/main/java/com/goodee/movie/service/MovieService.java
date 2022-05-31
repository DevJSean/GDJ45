package com.goodee.movie.service;

import java.util.Map;

public interface MovieService {

	public Map<String, Object> findMovies();
	
	public Map<String, Object> findMovie(Map<String, Object> map);
	
}
