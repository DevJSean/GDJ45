package com.goodee.ex06.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.service.BoardService;

@Controller
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

		logger.info("list(): " + boards); // 콘솔창에 정보를 찍어준다.
		
		model.addAttribute("boards", boards);
		return "board/list"; // board 폴더 밑에 list.jsp
	}
	
	@GetMapping("/board/detail")                                            
    public String detail(@RequestParam Long board_no, Model model) {                      
      BoardDTO board = boardService.findBoardByNo(board_no);
      logger.info("detail(): " + board);
      model.addAttribute("board", board);                    
      return "board/detail";                                 
    }   
	
	
	
}
