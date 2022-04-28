package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Now implements MyService {

	// 메소드
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 응답
		String now = new SimpleDateFormat("a h:mm:ss").format(new Date());
		
		// 응답 결과는 request에 속성(Attribute)으로 저장한다.
		request.setAttribute("result", now);
		
		// 어디로 갈 것인가?
		ActionForward af = new ActionForward();
		af.setView("views/output.jsp"); 
		
		// 어떻게 갈 것인가?
		af.setRedirect(false); // forward
		
		// ActionForward 반환
		return af;
		
	}
	
}
