package com.goodee.ex12.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.ex12.domain.BoardDTO;
import com.goodee.ex12.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public String list(HttpServletRequest request, Model model) {
		
		// 상세보기, 삭제 등에서 session 정보를 사용하고 목록으로 넘어올텐데, 넘어왔을 때 그 정보들을 지우기.(더 이상 쓸 일이 없으니까)
		// 필수 작업은 아님. 세션 유지 시간이 지나면 자동으로 사라진다.
		request.getSession().removeAttribute("board");

		boardService.findBoards(request, model);
		return "board/list";
	}
	
	@GetMapping("/board/savePage")
	public String savePage() {
		return "board/save";
	}
	
	@PostMapping("/board/save")
	//public void save(HttpServletResponse response) { // 반환 타입이 void이고, response를 사용한다. -> service에서 직접 이동 & 메세지 보여주기 (PrintWriter out)
	//}
	//public String save() {
	//	return "redirect:/board/list"; // 메세지 없이 여기서 이동 redirect
	//}
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("insRes", boardService.save(request));
		return "redirect:/board/result";  // 매핑 /board/result로 redirect한다.
	}                  // ↓                
	                   // ↓
	@GetMapping("/board/result")
	public String result() {
		return "board/result"; // board/result.jsp로 이동한다.
	}
	
	@GetMapping("/board/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model) {
		boardService.findBoardByNo(request, response, model);
		return "board/detail"; // board/detail.jsp로 이동
	}
	
	@GetMapping("/board/changePage")
	public String changePage() {
		return "board/change";
	}
	
	/*      request로 처리해보자.
	@PostMapping("/board/change")
	public String change(HttpServletRequest request) {
		BoardDTO board = BoardDTO.builder()
				.boardNo(Long.parseLong(request.getParameter("boardNo")))
				.title(request.getParameter("title"))
				.content(request.getParameter("content"))
				.build();
		boardService.change(board);
		return "redirect:/board/result";
	}*/
	@PostMapping("/board/change")
	public String change(BoardDTO board, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("updRes", boardService.change(board));
		return "redirect:/board/result";
	}
	
	@GetMapping("/board/remove")
	public String remove(@RequestParam(value="boardNo", required=false, defaultValue="0") Long boardNo
						, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("delRes", boardService.remove(boardNo));
		return "redirect:/board/result";
	}
	
}
