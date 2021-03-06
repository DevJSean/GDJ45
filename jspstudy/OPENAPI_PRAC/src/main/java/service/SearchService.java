package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SearchService implements OpenAPIService {

	// 예외 시 search.jsp의 $.ajax의 error로 넘어간다.
	private void error(HttpServletResponse response, String msg) throws IOException{
		response.setContentType("text/plain; UTF-8");
		PrintWriter out = response.getWriter();
		out.write(msg);
		out.flush();
		out.close();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 파라미터 처리
		String query = request.getParameter("query");
		String display = request.getParameter("display");
		String sort = request.getParameter("sort");
		
		// 검색어 인코딩 UTF-8
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			error(response, "검색어 인코딩 실패");
		}
		
		// API 접속
		String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + query + "&display=" + display + "&sort=" + sort;
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			error(response, "잘못된 형식의 apiURL을 작성했을 때");
		} catch (IOException e) {
			error(response, "apiURL 연결 실패했을 때");
		}
		
		// 요청 header + method
		try {
			con.setRequestMethod("GET"); // 대문자로 작성
			con.setRequestProperty("X-Naver-Client-Id", "C4eZVVUAQJzuEShVgST4");
			con.addRequestProperty("X-Naver-Client-Secret", "v8KS5FKYHn");
		} catch (Exception e) {
			error(response, "API 요청 실패");
		}
		
		// 응답할 스트림(성공하면 정상 스트림, 실패하면 에러 스트림)
		BufferedReader br = null;
		int responseCode = con.getResponseCode(); // HTTP 코드
		if(responseCode == 200) { // 200 == HttpURLConnection.HTTP_OK
			br = new BufferedReader(new InputStreamReader(con.getInputStream())); // byte 기반의 stream을 쓰면 한글은 깨진다
																                  // char 기반의 stream으로 바꿨다. (char 기반은 이름에 Reader가 들어간다.)
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// 응답(네이버에서 알려준 검색결과)
		StringBuilder sb = new StringBuilder();
		try {
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);       // 최종 응답 결과는 sb에 들어간다
			}
			br.close();
		} catch (IOException e) {
			error(response, "API 응답 실패");
		}
		
		// API연결 종료 (네이버 측과 할 일이 끝남)
		con.disconnect();
		
		// 응답(네이버에서 알려준 검색결과)을 search.jsp로 보내기
		// 응답은 sb에 저장되어 있다.
		response.setContentType("application/json; charset=UTF-8"); // request에서 json으로 달라고 해서 json으로 응답 나온다
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
			
	}
}
