package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AddService;
import service.DetailService;
import service.ListService;
import service.MemberService;
import service.ModifyService;
import service.RemoveService;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		MemberService service = null;
		ActionForward af = null;
		
		switch(command) {
		case "memberManage.do":
			af = new ActionForward("member/manage.jsp", false);
			break;
			
		case "add.do":
			service = new AddService();
			break;
		case "list.do": // http://localhost:9090/AJAX/list.do
			service = new ListService();
			break;
		case "detail.do": // http://localhost:9090/AJAX/detail.do?no=1
			service = new DetailService();
			break;
		case "modify.do":
			service = new ModifyService();
			break;
		case "remove.do":
			service = new RemoveService();
			break;
		}
		
		// MemberService와 그 외의 Service들이 throws IOEXCEPTION을 하고 있어서 service를 호출하는 이 곳에서 try catch를 해야 함
		// 하지만 여기서 try catch 예외처리를 하지 않는데, 그 이유는 doGet에서 예외를 던지고 tomcat이 처리한다.
		if(service != null) {
			service.execute(request, response);  // MemberService의 execute 반환값이 void이기 때문에 
		}										 // af 값으로 반환하지 않고, 실행만 한다
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
