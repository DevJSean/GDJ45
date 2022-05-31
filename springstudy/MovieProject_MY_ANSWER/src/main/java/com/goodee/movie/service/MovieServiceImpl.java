package com.goodee.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.mapper.MovieMapper;
import com.goodee.movie.util.SecurityUtils;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;

	@Override
	public Map<String, Object> findMovies() {

		Map<String, Object> map = new HashMap<>();
		map.put("message", "전체 " + movieMapper.selectMovieCount() + "개의 목록을 가져왔습니다.");
		map.put("list", movieMapper.selectMovieList());
		map.put("status", 200);
		
		return map;
	}
	
	@Override
	public Map<String, Object> findMovie(Map<String, Object> map) {
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("column", map.get("column").toString());
		searchMap.put("query", SecurityUtils.XSS(map.get("query").toString()));
		
		int count = movieMapper.selectMovie(searchMap).size();
		List<MovieDTO> list = movieMapper.selectMovie(searchMap);
		
		if(list.isEmpty()) {
			Map<String, Object> map2 = new HashMap<>();
			map2.put("message", map.get("query").toString()+"검색 결과가 없습니다.");
			map2.put("list", null);
			map2.put("status", 500);
			return map2;
		} else {
			Map<String, Object> map2 = new HashMap<>();
			map2.put("message", count+"개의 검색결과가 있습니다.");
			map2.put("list", list);
			map2.put("status", 200);
			return map2;
		}
		

	}
	
	
}
