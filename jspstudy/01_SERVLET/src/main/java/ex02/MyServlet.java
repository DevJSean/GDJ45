package ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청(request)
		
		// 1) 요청에 포함된 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 2) 파라미터(Parameter) 확인
		//    (1) URL?파라미터=값
		//    (2) URL?파라미터=값&파라미터=값
		//    (3) 모든 파라미터는 String이다.
		//    (4) 파라미터 확인 메소드는 getParameter()이다.
		String name = request.getParameter("name");
		
		String strAge = request.getParameter("age"); // 숫자 값은 형변환을 해줘야 한다.
		int age = 0;
		if(strAge != null) {                // 파라미터 age에 전달된 값이 없을 경우 null이 나올 것
			age = Integer.parseInt(strAge); // 그 때 int로 형변환을 하려고 하면 nullPointerException 나온다
		}                                   // null 값을 회피하는 작업을 해줘야 한다.
		// 3) 파라미터 콘솔에 출력하기
		System.out.println("이름 " + name);
		System.out.println("나이 " + age);
		
		// 2. 응답(response)
		//    사용자에게 보여 줄 화면 만들기(HTML 문서 만들기)
		
		// 1) HTML 문서 타입
		response.setContentType("text/html");
		
		// 2) 응답에 포함될 한글 처리
		response.setCharacterEncoding("UTF-8");
		
		// 참고. HTML 문서 타입 + 한글 처리
		// response.setContentType("text/html; charset=UTF-8");
		
		// 3) HTML 문서 만들기 = 태그 만들기 
		//    (1) 문자 기반 출력 스트림(Writer)을 이용해서 태그를 만든다.
		//    (2) PrintWriter를 (주로) 이용한다. 
		//    (3) response.getWriter()를 이용해서 출력 스트림을 만든다.
		//    (4) IOException 예외 처리가 필요하다. doGet() 메소드가 이미 처리하고 있다.
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>나의 첫 응답</title>");
		out.println("<style>"); //"<style type=\"text/css\">"  타입 생략 가능
		out.println("body { background-color: yellow; }");
		out.println("</style>");
		out.println("<script>"); //"<script type=\"text/javascript\">"  타입 생략 가능
		out.println("alert('이름과 나이를 확인하세요.');");
		out.println("</script>");
		out.println("<body>");
		out.println("<h1>이름 : " + name + "</h1>");
		out.println("<h1>나이 : " + age + "</h1>");
		out.println("</body>");
		out.println("</html>");
		
		out.flush(); // 출력스트림 out에 남아 있는 데이터 모두 내보내기
		out.close(); // out은 스트림이기 때문에 닫아줘야 한다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
