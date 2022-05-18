package com.goodee.prac01.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.goodee.prac01.domain.BoardDTO;
import com.goodee.prac01.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<BoardDTO> findBoards() {
		return boardRepository.selectBoards();
	}
	
	@Override
	public Long selectBoardCount() {
		return boardRepository.selectBoardCount();
	}
	

	@Override
	public BoardDTO findBoardByNo(Long no) {
		return boardRepository.selectBoardByNo(no);
	}

	@Override
	public void save(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		int res = boardRepository.insertBoard(board, request);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('등록되었습니다')");
				out.println("location.href='" + request.getContextPath() + "/board/list'"); // location 이동은 redirect와 같은 방식의 이동이다.
				out.println("</script>");               // contextPath 넘겨주기 위해 request가 사용된 것
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('등록되지 않았습니다.')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		int res = boardRepository.updateBoard(board);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('수정되었습니다')");
				out.println("location.href='" + request.getContextPath() + "/board/list'"); // location 이동은 redirect와 같은 방식의 이동이다.
				out.println("</script>");               // contextPath 넘겨주기 위해 request가 사용된 것
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('수정되지 않았습니다.')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Long no, HttpServletRequest request, HttpServletResponse response) {
		int res = boardRepository.deleteBoard(no);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삭제되었습니다')");
				out.println("location.href='" + request.getContextPath() + "/board/list'"); // location 이동은 redirect와 같은 방식의 이동이다.
				out.println("</script>");               // contextPath 넘겨주기 위해 request가 사용된 것
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('삭제되지 않았습니다.')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
