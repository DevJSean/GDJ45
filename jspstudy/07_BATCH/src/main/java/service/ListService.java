package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class ListService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// DB에서 가져온 학생 리스트 목록을 list라는 이름으로 request에 속성 저장
		request.setAttribute("list", StudentDAO.getInstance().selectStudentList());
		
		request.setAttribute("totalCount", StudentDAO.getInstance().getTotalCount());
		request.setAttribute("totalAverage", StudentDAO.getInstance().getTotalAverage());
		
		// request에 속성으로 저장한 정보를 list.jsp로 forward 한다
		return new ActionForward("student/list.jsp", false);
	}

}
