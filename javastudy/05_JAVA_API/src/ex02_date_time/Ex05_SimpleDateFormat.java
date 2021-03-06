package ex02_date_time;

import java.text.SimpleDateFormat;
import java.util.Date;

// util.Date나 sql.Date의 형식을 보기 편하게 formatting하기.

public class Ex05_SimpleDateFormat {

	public static void main(String[] args) {

		// SimpleDateFormat 클래스
		// 1. java.text 패키지에 저장된 클래스이다.
		// 2. 날짜/시간의 형식을 pattern으로 지정한다.
		// 3. pattern
		//    1) 년    		  : yyyy, yy   (소문자 주의)
		//    2) 월    		  : MM, M      (대문자)
		//    3) 일    		  : dd, d
		//    4) 요일(일~토)  : E
		//    5) 오전/오후    : a
		//    6) 시(1~12)	  : hh, h
		//    7) 시(0~23)     : HH, H
		//    8) 분           : mm, m      (소문자)
		//    9) 초           : ss, s
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일"); // 메소드 SimpleDateFormat(String pattern) 자동완성
		// import 신경쓰기.
		
		String res = sdf.format(now);
		System.out.println(res);
		

	}

}
