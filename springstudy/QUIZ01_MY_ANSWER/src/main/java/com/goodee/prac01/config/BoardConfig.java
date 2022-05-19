package com.goodee.prac01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.prac01.repository.BoardRepository;
import com.goodee.prac01.service.BoardService;
import com.goodee.prac01.service.BoardServiceImpl;

@Configuration
public class BoardConfig {

	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public BoardRepository boardRepository() {
		return new BoardRepository();
	}
	
}
