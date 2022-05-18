package com.goodee.ex07.controller;

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

import com.goodee.ex07.domain.BoardDTO;
import com.goodee.ex07.service.BoardService;

@Controller
public class BoardController {
	
	// 컨트롤러에서
	// HttpServletResquest, HttpServletResponse, HttpSession을 선언할 수 있다.
	

	// logger
	// System.out.println() 대체 (System.out.println()은 완성품에 남아있으면 안 된다.)
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class); // BoardController에 기록을 남겨주는 logger
	
	// BoardService boardService : DI 의존성 주입  (BoardConfig.java에 저장된 bean을 가져오기.)
	// 1. 필드 주입   : @Autowired private BoardService boardService;
	// 2. 생성자 주입 : BoardController에 @AllArgsConstructor 추가
	// 3. setter 주입 : setter 코드를 직접 추가한 뒤 @Autowired 추가(@Setter 사용 불가)
	@Autowired
	private BoardService boardService; // BoardConfig bean과 이름 맞추기.
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/list")
	public String list(Model model) {
		List<BoardDTO> boards = boardService.findBoards();
		logger.info("list(): " + boards);  // 콘솔에 정보를 찍어준다.
		model.addAttribute("boards", boards);
		return "board/list"; //board 폴더 밑에 list.jsp
	}
	
	@GetMapping("/board/detail")
	public String detail(@RequestParam(value="board_no") Long board_no, Model model) {
		BoardDTO board = boardService.findBoardByNo(board_no);
		logger.info("detail(): " + board);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/board/addPage")
	public String addPage() {
		return "board/add"; // board/add.jsp로 이동
	}
	
	@PostMapping("/board/add")
	public void add(BoardDTO board, HttpServletRequest request, HttpServletResponse response) { // 여기서 request와 response를 선언하고 service가 받는 것이다.
		logger.info("add(): " + board);                                                         // contextPath 알아내기 request, 응답 보내주기 response
		boardService.save(board, request, response);
		
		// 삽입 후에는 redirect를 해야 하는데,
		// redirect가 없다는 것은 boardService의 save() 메소드에서 직접 이동했다는 것을 의미한다.
		// 응답을 만드는 response를 save() 메소드에 넘겨주면 save() 메소드에서 직접	이동할 수 있다.
	}
	
	@GetMapping("/board/remove")                
	// @RequestParam(value="board_no", required=false, defaultValue="0") 
	// 파라미터 board_no가 오지 않는다면 0을 사용하겠습니다.  (이전에 했던 Optional 코드의 스프링 버전)
	public void remove(@RequestParam(value="board_no", required=false, defaultValue="0") Long board_no, HttpServletRequest request, HttpServletResponse response) {
		logger.info("remove(): " + board_no);
		boardService.remove(board_no, request, response);
		
		// save() 메소드와 마찬가지로 remove() 메소드에서 직접 이동한다.
	}
	

	@PostMapping("/board/modifyPage")
	public String modifyPage(BoardDTO board, Model model) { // detail.jsp에서 board_no, title, content 정보를 BoardDTO로 받아오고, 이를 Model로 modify.jsp로 보내줌
		logger.info("modifyPage(): " + board);
		model.addAttribute("board", board); // 정보를 "board"라는 이름으로 model에 저장
		return "board/modify"; // board/modify.jsp로 forward
	}
	
	/*
	@GetMapping("/board/modifyPage")
	public String modifyPage(@ModelAttribute(value="board") BoardDTO board) {
		logger.info("modifyPage(): " + board);
		return "board/modify";
	} */
	
	@PostMapping("/board/modify")
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse rseponse) {
		logger.info("modify() : " + board);
		boardService.modify(board, request, rseponse);
		// save(), remove() 메소드와 마찬가지로 modify() 메소드에서 직접 이동한다.
	}
	
	
}


