package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActionForward;
import model.Lotto;
import model.MyService;
import model.Now;
import model.Today;

// suffix값이 .do인 모든 URLMapping(요청)을 모두 처리하겠다는 말.
@WebServlet("*.do")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답의 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// /MVC/today.do   /MVC/now.do 구분하는 방법
		String requestURI = request.getRequestURI();                     // /MVC/today.do
		String contextPath = request.getContextPath();                   // /MVC
		String command = requestURI.substring(contextPath.length() + 1); // today.do
		
		// 모든 model은 MyService 인터페이스를 구현하는 클래스이므로,
		// MyService 타입의 인스턴스이다.
		MyService service = null;
		
		// ActionForward 인스턴스
		ActionForward af = null;
		
		switch(command) {
			// model이 사용되지 않는 단순 이동
			case "input.do":
				// input.jsp로 이동만 하면 된다.
				af = new ActionForward();
				af.setRedirect(false);
				af.setView("views/input.jsp");
				break;
	
			// model이 사용되는 경우	
			case "today.do": 
				service = new Today();
				break;
				
			case "now.do":
				service = new Now();
				break;
				
			case "lotto.do":
				service = new Lotto();
				break;
		}
		// model의 실행(execute() 메소드의 호출)
		if(service != null) {
			af = service.execute(request, response);     
			// af.setView 
			// af.setRedirect
		}
		
		// model이 반환한 어디로 어떻게 정보(ActionForward)를 이용해서 이동한다.
		// af가 null인 경우가 있다. (model에서 직접 response를 이용해 응답한 경우, ajax 처리)
		if(af != null) {
			if(af.isRedirect()) {					 // redirect이라면
				response.sendRedirect(af.getView()); // af의 view에 있는 곳으로 이동  ( getView() )
			} else {																	// forward라면
				request.getRequestDispatcher(af.getView()).forward(request, response);  // forward하기. 이동할 경로는 view에 있음 ( getView() )
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
