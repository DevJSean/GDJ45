package com.goodee.ex05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.ex05.domain.BoardDTO;
import com.goodee.ex05.service.BoardService;

/*
 	@RestController
 	
 	Controller 대신 이걸 선언하면 Ajax 처리를 위한 컨트롤러라고 생각하면 된다.
 	이 선언을 하면 모든 메소드에 @ResponseBody 애너테이션을 자동으로 붙여준다.
  
  	그래서 @RestController를 선언하게 되면
  	index.jsp나
  	작업하는 jsp로 이동하는 메소드는 다른 컨트롤러에 두어야 한다.
  	다른 컨트롤러에 쌩뚱맞은 메소드를 넣어도 상관이 없다.
  
*/


@RestController
public class BoardController {
	
	/*
	private BoardService boardService;
	
	// 생성자는 @Autowired를 안 적어도 됩니다.
	public BoardController(BoardService boardService) {  // root-context.xml에 정의한 bean이 매개변수 BoardService boardService로 주입됩니다.
		super();
		this.boardService = boardService;
	}
	 */
	
	@Autowired
	private BoardService boardService;
	

	
	@GetMapping(value="/board/detail1", produces="application/json; charset=UTF-8")
	//@ResponseBody (@RestController 사용하면 안 적어도 된다.)
	public BoardDTO detail1(HttpServletRequest request) {
		BoardDTO board = boardService.detail1(request);
		
		return board;
	}
	
	@GetMapping(value="/board/detail2", produces="application/json; charset=UTF-8")
	//@ResponseBody
	public BoardDTO detail2(@RequestParam(value="title") String title,
							@RequestParam(value="hit") Long hit) {
		BoardDTO board = boardService.detail2(title, hit);
		
		return board;
	}
	
	@PostMapping(value="/board/detail3", produces="application/json; charset=UTF-8")
	//@ResponseBody
	public Map<String, Object> detail3(@RequestBody BoardDTO board) {
		Map<String, Object> res = boardService.detail3(board);
		
		return res;
	}
	
	@PostMapping(value="/board/detail4", produces="application/json; charset=UTF-8")
	//@ResponseBody
	public BoardDTO detail4(@RequestBody Map<String, Object> map) {
		
		BoardDTO board = boardService.detail4(map);
		
		return board;
	}
}
