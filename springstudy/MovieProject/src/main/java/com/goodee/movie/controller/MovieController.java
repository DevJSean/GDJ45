package com.goodee.movie.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.service.MovieService;
import com.goodee.movie.util.SecurityUtils;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	
	@GetMapping("/")
	public String movie() {
		return "movie";
	}
	
	@GetMapping(value="/movie/searchAllMovies", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> searchAllMovies() {
		Map<String, Object> res = movieService.findMovies();
		
		return res;
	}
	
	@PostMapping(value="/movie/searchMovie", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> searchMovie(@RequestBody Map<String, Object> map) {
		return movieService.findMovie(map);
	}
}
