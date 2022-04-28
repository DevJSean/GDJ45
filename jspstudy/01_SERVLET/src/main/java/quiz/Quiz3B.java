package quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz3B")
public class Quiz3B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz3B() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청 확인
		request.setCharacterEncoding("UTF-8");
		
		String from = request.getParameter("from");
		if(from.isEmpty()) { 
			from = "지시현";
		}
		String to = request.getParameter("to");
		if(to.isEmpty()) {
			to = "여자친구";
		}
		String content = request.getParameter("content");
		
		// 파일 만들기
		// 저장할 디렉토리
		File dir = new File("C:\\GDJ45\\jspstudy\\01_SERVLET\\storage");
		if(dir.exists() == false) {
			dir.mkdirs(); //디렉토리가 없으면 만들어라
		}
		
		// 저장할 파일명
		String today = new Date(System.currentTimeMillis()) + ""; //날짜형에 빈 문자열을 연결해서 String으로 변환
		String filename = today + "_" + from + ".txt"; // sql.Date로 생성
		
		// 파일 인스턴스(객체) 생성
		File file = new File(dir, filename);
		
		// 출력 스트림 생성(문자 기반)
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		// 파일의 내용 생성
		bw.write("작성일 : " + today);
		bw.newLine(); // 줄바꿈
		bw.write("보내는 사람 : " + from);
		bw.newLine();
		bw.write("받는 사람 : " + to + "\n");
		bw.newLine();
		bw.write(content);
		
		if(bw != null)
			bw.close();
			
		// 2. 응답
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
	
		out.println("<script>");
		out.println("alert('" + file.getName() + " 파일이 생성되었습니다.')");
		out.println("history.back()");
		out.println("</script>");

		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
