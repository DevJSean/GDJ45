package ex06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/ResponseJSONServlet")
public class ResponseJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResponseJSONServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try { //NumberFormatException 처리
			
			// 요청
			request.setCharacterEncoding("UTF-8");
			
			String name = request.getParameter("name");
			String strAge = request.getParameter("age");
			int age = Integer.parseInt(strAge);
			
			// 응답
			
			response.setContentType("application/json; charset=UTF-8"); // 제이슨의 타입
			
			JSONObject obj = new JSONObject(); // json 외부 라이브러리를 프로젝트에 넣고 가능함
			obj.put("name", name);
			obj.put("age", age);
			System.out.println(obj.toString());
			
			PrintWriter out = response.getWriter();
			
			out.println(obj);            // 객체로
			//out.println(obj.toString()); // 문자열로
			
			out.flush();
			out.close();
		} catch(NumberFormatException e) {
			
			// 응답 
			response.setContentType("text/plain; charset=UTF-8");
			
			// 예외코드
			response.setStatus(2000); // 코드 임의로 만들기
			
			// 예외메시지
			PrintWriter out = response.getWriter();
			out.println("age는 정수로 전달해주세요");
			
			out.flush();
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
