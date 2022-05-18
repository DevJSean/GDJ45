package com.goodee.ex06.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.service.BoardService;

@Controller
// @RequestMapping("/board") 이 컨트롤러의 매핑들은 모두 /board가 붙어있다.  @RequestMapping을 붙이면 밑에서 /board 다 지워줘야 한다.
public class BoardController {

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
	// @RequestParam(value="board_no", required=false, defaultValue="0") 
	// 파라미터 board_no가 오지 않는다면 0을 사용하겠습니다.  (이전에 했던 Optional 코드의 스프링 버전)
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") Long board_no, Model model) {
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
	public String add(BoardDTO board) {
		logger.info("add(): " + board);                                                        
		boardService.save(board);

		// 삽입 후 목록보기로 redirect 하기 위해서 
		// 목록보기의 매핑을 확인한다. (/board/list)
		// redirect는 매핑으로 이동하기 때문에 아래와 같이 작성한다.
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/remove")                
	// @RequestParam(value="board_no", required=false, defaultValue="0") 
	// 파라미터 board_no가 오지 않는다면 0을 사용하겠습니다.  (이전에 했던 Optional 코드의 스프링 버전)
	public String remove(@RequestParam(value="board_no", required=false, defaultValue="0") Long board_no) {
		logger.info("remove(): " + board_no);
		boardService.remove(board_no);
		
		// 삭제 후에는 목록보기로 redirect 한다.
		return "redirect:/board/list";
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
	public String modify(BoardDTO board) {
		logger.info("modify() : " + board);
		boardService.modify(board);
		
		// 수정 후에는 상세보기로 redirect 한다.
		return "redirect:/board/detail?board_no=" + board.getBoard_no();
	}
	
	
}


