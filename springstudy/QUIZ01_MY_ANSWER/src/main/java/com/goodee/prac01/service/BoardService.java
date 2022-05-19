package com.goodee.prac01.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodee.prac01.domain.BoardDTO;


public interface BoardService {

	public List<BoardDTO> findBoards();
	public Long selectBoardCount();
	public BoardDTO findBoardByNo(Long board_no);
	public int updateHit(Long no);
	public void save(BoardDTO board, HttpServletRequest request, HttpServletResponse response);
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response);
	public void remove(Long board_no, HttpServletRequest request, HttpServletResponse response);

	
}
