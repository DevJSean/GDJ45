package ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnchorServlet
 */
@WebServlet("/AnchorServlet")
public class AnchorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnchorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청(request) 처리
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name"); // html 문서에서 파라미터가 없는 주소에 해당하는 a태그를 누르면 null 값이 전달될 것임
		String strAge = request.getParameter("age");
	
		// 요청 파라미터 디폴트 처리하기
		// = 전달되지 않은 파라미터의 기본값 지정하기
		
		// name은 "아무개", age는 0을 기본값으로 사용
		if(name == null) {
			name = "아무개";
		}
		if(strAge == null) {
			strAge = "0";
		}
		
		int age = Integer.parseInt(strAge);
		
		// 2. 응답(response) 만들기
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>"); // 응답 메세지만 만들어서 보내줄 때 스크립트 태그만 만든다
		out.println("alert('이름은 " + name + "이고, 나이는 " + age + "살입니다.')"); // 경고창
		out.println("history.back()"); // 이전 페이지로 돌아가기
		out.println("</script>");
		
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
