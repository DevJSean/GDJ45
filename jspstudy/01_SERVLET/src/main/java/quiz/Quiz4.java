package quiz;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Quiz4")
public class Quiz4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz4() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		
		String clientId = "C4eZVVUAQJzuEShVgST4";
		String clientSecret = "v8KS5FKYHn";
		
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		
		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("인코딩 실패");
			out.flush();
			out.close();
		}
		
		URL url = null;
		HttpURLConnection con = null;	
		
		// 접속 부분
		try {  
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection(); // openConnection이 부모 타입이므로 자식 타입으로 다운캐스팅			
		} catch(MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다.");
			out.flush();
			out.close();
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("연결이 실패했습니다.");
			out.flush();
			out.close();
		}
		
		// api 요청
		try {
			String postParams = "source=" + source + "&target=" + target + "&text=" + text;
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			//wr.write(postParams.getBytes()); // 바이트 배열 단위(byte [])로 보내야 함. String 불가
			wr.write(postParams.getBytes());
			wr.flush();
			if(wr != null)
				wr.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다.");
			out.flush();
			out.close();
		}
		
		// api 응답
		StringBuilder sb = new StringBuilder(); // 텍스트 읽어들이기
		try {
			InputStreamReader streamReader = null;
			// 정상 동작일 때
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK = 200
				streamReader = new InputStreamReader(con.getInputStream()); // 바이트 기반(con.getInputStream())에서 text 기반(InputStreamReader()으로 변환
			} else { // 오류 났을 때
				streamReader = new InputStreamReader(con.getErrorStream());
			}
			BufferedReader br = new BufferedReader(streamReader);
			String line = null;
			while((line = br.readLine()) != null) { // null이 아니면 계속 읽어들일 라인이 있다.
				sb.append(line);
				// sb에 들어 있는 내용을 html의 resDate로 보내야 함
			}
			if(br != null)
				br.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 응답을 읽는데 실패했습니다.");
			out.flush();
			out.close();
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
