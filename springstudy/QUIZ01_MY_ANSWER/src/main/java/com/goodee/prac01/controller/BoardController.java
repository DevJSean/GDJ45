package com.goodee.prac01.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.prac01.domain.BoardDTO;
import com.goodee.prac01.service.BoardService;


@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class); // BoardController에 기록을 남겨주는 logger
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/list")
	public String list(Model model) {
		List<BoardDTO> boards = boardService.findBoards();
		Long count = boardService.selectBoardCount();
		logger.info("list(): " + boards);  
		logger.info("count : " + count);  
		model.addAttribute("boards", boards);
		model.addAttribute("count", count);
		return "board/list"; //board 폴더 밑에 list.jsp
	}
	
	@GetMapping("/board/detail")
	public String detail(@RequestParam(value="no") Long no, Model model) {
		BoardDTO board = boardService.findBoardByNo(no);
		boardService.updateHit(no);
		logger.info("detail(): " + board);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/board/addPage")
	public String addPage() {
		return "board/add"; // board/add.jsp로 이동
	}
	
	@PostMapping("/board/add")
	public void add(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		logger.info("add(): " + board);                                                        
		boardService.save(board, request, response);
	}
	
	@GetMapping("/board/remove")                
	public void remove(@RequestParam(value="no", required=false, defaultValue="0") Long no, HttpServletRequest request, HttpServletResponse response) {
		logger.info("remove(): " + no);
		boardService.remove(no, request, response);
	}
	
	@PostMapping("/board/modify")
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse rseponse) {
		logger.info("modify() : " + board);
		boardService.modify(board, request, rseponse);
	}
	
	
	
	
	
}
