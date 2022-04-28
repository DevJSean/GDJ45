package service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.EmpDAO;

public class DetailService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 파라미터 no의 null처리
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(optNo.orElse("0"));
		
		// 사원번호 no를 가진 사원정보를 request에 속성으로 저장하고,
		// detail.jsp로 forward 하기
		request.setAttribute("emp", EmpDAO.getInstance().selectEmpByNo(no));
		
		// session에 저장시켜 놓으면 어느 페이지에서든지 다 불러낼 수 있다.
		/*		
		HttpSession session = request.getSession(); // request로부터 session 정보를 알아내기
		session.setAttribute("emp", EmpDAO.getInstance().selectEmpByNo(no));
		*/
		
		return new ActionForward("emp/detail.jsp", false);
	}

}
