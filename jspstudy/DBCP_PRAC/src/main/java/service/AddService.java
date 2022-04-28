package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDAO;

public class AddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 사용자가 입력한 값을
		String writer = request.getParameter("writer");
		// String writer = (Member)(session.getAttribute("login")).getName(); 나중에는 이렇게
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr(); // ip 주소
		
		// DTO로 만들기
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		board.setIp(ip);
		
		// 0 혹은 1
		int res = BoardDAO.getInstance().insertBoard(board);

		// 삽입 결과 페이지로 삽입 결과(res)를 보냄
		return new ActionForward("board/insertResult.jsp?res=" + res, true); // redirect는 데이터를 넘기려면 직접 파라미터를 붙이는 방법 밖에 없다
	}

}
