package com.goodee.ex06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex06.repository.BoardRepository;
import com.goodee.ex06.service.BoardService;
import com.goodee.ex06.service.BoardServiceImpl;

@Configuration
public class BoardConfig {

	@Bean
	public BoardService boardService() {  //Controller DI 이름 맞추기
		return new BoardServiceImpl();
	}
	
	@Bean
	public BoardRepository boardRepository() {  //ServiceImpl DI 이름 맞추기
		return new BoardRepository();
	}
	
}




