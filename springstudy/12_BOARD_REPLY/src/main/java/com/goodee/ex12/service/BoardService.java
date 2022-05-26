package com.goodee.ex12.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.goodee.ex12.domain.BoardDTO;

public interface BoardService {

	public void findBoards(HttpServletRequest request, Model model); // 목록은 model에 실어서 controller에 보내고
																	 // 반환 타입은 없는 것으로 한다.
																	 // model에 싣겠다 -> MVC 패턴
	public void findBoardByNo(HttpServletRequest request, HttpServletResponse response, Model model);
	
	public int save(HttpServletRequest request); // BoardDTO가 제일 편하겠지만, IP 때문에 request를 쓴다.
	
	public int change(BoardDTO board);
	
	public int remove(Long boardNo);
}
