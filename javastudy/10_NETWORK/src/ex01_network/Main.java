package ex01_network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

	// URL
	// 1. Uniform Resource Locator : 정형화(모양이 정해져 있다)된 자원의 위치
	// 2. 쉽게 말해서 인터넷 주소 체계를 의미한다
	// 3. 구성
	//		프로토콜://     호스트     / 서버 경로  ? 파라미터
	//		   https://search.naver.com/search.naver?~~~query=날씨
	
	
	public static void m1() {
		
		// URL 인스턴스 사용시 예외처리 필수
		try {
			String apiUrl = "https://search.naver.com/search.naver?query=날씨";
			URL url = new URL(apiUrl); //작성한 주소가 올바른 형태인지 확인하기 위해 예외처리
		
			System.out.println("프로토콜 : " + url.getProtocol());
			System.out.println("호스트 : " + url.getHost());
			System.out.println("파일 : " + url.getFile()); // 서버에 있는 파일의 경로
			System.out.println("쿼리 : " + url.getQuery());

		} catch (MalformedURLException e) {  //MalformedURLException 잘못된 형태의 주소가 전달 됐을 때의 예외
			System.out.println("API 주소 오류");
		}
	}
	
	public static void m2() {
		
		// HttpURLConnection : 실제 접속을 담당
		
		try {
			
			String apiUrl = "https://www.naver.com";
			URL url = new URL(apiUrl);
			
			// 부모 자식 관계가 반대라서 캐스팅을 해줘야 한다.
			HttpURLConnection con = (HttpURLConnection)url.openConnection(); // URLConnection: 부모, HttpURLConnection: 자식
			
			if(con.getResponseCode() == 200) // 200이 뜨는데 정상적으로 접속이 되었다는 의미이다.
				System.out.println("API 접속 성공");  
			
			con.disconnect(); //접속은 닫아야 함.
			
		} catch (MalformedURLException e) { // URL
			System.out.println("API 주소 오류"); // 주소 잘못 썼을 때
		} catch (IOException e) { // HttpURLConnection
			System.out.println("API 접속 오류"); // 접속이 안될 때
		}
		
	}
	
	
	public static void m3() {
		
		// 파일 내려받기(다운로드)
		// 기상청 XML, 날씨에 관한 XML 데이터
		// 이 파일을 자바로 내려받으려는 것.
		// http://www.kma.go.kr/XML/weather/sfc_web_map.xml
		
		try {
			
			String apiUrl = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";
			URL url = new URL(apiUrl);
			
			//접속
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 입력 스트림 생성(URL의 입력 스트림은 바이트 기반이다)
			InputStream in = con.getInputStream(); // 입력 스트림을 접속으로부터 가지고 온다. -> 접속된 곳의 내용을 알 수 있게 된다.
			
			// 문자 기반 입력 스트림 생성( 바이트 타입을 String으로)
			InputStreamReader isr = new InputStreamReader(in);
			
			// 입력 스트림에 버퍼 추가
			BufferedReader br = new BufferedReader(isr);  //입력 스트림 생성~ 입력스트림 버퍼추가까지 한줄로만든다.
			
			// 여기까지 사이트 읽기 해결
			// 지금부터 내려받기 
			
			// 생성할 파일의 디렉토리 작업
			File dir = new File("C:\\storage\\");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			// 생성할 파일명 == URL 통해서 확인
			String filename = url.getFile(); // getFile()을 통해 나오는 건 "/XML/weather/sfc_web_map.xml"
			String[] tokens = filename.split("/");   // sfc_web_map.xml을 빼내야 함.  -> split(), subString()
			System.out.println(tokens[tokens.length - 1]); // /를 기준으로 나눴을 때 마지막 요소를 빼야함
			File file = new File(dir, tokens[tokens.length - 1]);			
			
			// 문자 기반 출력 스트림 생성
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			// 내려 받기
			// br에서 내려 받은 내용을 bw로 보낸다
			String line = null;
			while((line=br.readLine()) != null)
				bw.write(line + "\n");
			
			// 사용한 스트림/접속 닫기
			bw.close();
			br.close();
			con.disconnect();
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 서버 오류");
		}
	}
	
	// 인코딩/디코딩
	// 인코딩 : UTF-8 사용하여 암호화
	// 디코딩 : UTF-8 사용하여 복호화
	
	public static void m4() {
		
		String str1 = "자바 JAVA !@#$";
		String str2 = "자바+JAVA+!@#$";
		
		try {
			
			//인코딩
			String encodedStr1 = URLEncoder.encode(str1, "UTF-8");
			System.out.println("자바 JAVA !@#$ 인코딩 : " + encodedStr1);

			String encodedStr2 = URLEncoder.encode(str2, StandardCharsets.UTF_8);
			System.out.println("자바+JAVA+!@#$ 인코딩 : " + encodedStr2);
			
			//디코딩
			String decodedStr1 = URLDecoder.decode(encodedStr1, "UTF-8");
			// decodedStr1 = "자바 JAVA !@#$"
			System.out.println("디코딩 : " + decodedStr1);

			String decodedStr2 = URLDecoder.decode(encodedStr2, StandardCharsets.UTF_8);
			// decodedStr2 = "자바+JAVA+!@#$"
			System.out.println("디코딩 : " + decodedStr2);
			
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void main(String[] args) {

		//m1();
		//m2();
		//m3();
		m4();
		
		
	}

}
