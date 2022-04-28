package service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class DetailService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 전달 안 됐을 대 대비
		// 감싸기
		Optional<String> optStuNo = Optional.ofNullable(request.getParameter("stuNo"));
		// 풀기
		Long stuNo = Long.parseLong(optStuNo.orElse("0"));
		
		// 상세 정보를 db에서 가져와 request에 student라는 이름으로 싣겠다
		request.setAttribute("student", StudentDAO.getInstance().selectStudentByStuNo(stuNo));

		// 그리고 detail.jsp로 forward한다.
		return new ActionForward("student/detail.jsp", false);
	}

}
