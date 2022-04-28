package ex02_date_time;

import java.sql.Date;

public class Ex04_java_sql_Date {

	public static void main(String[] args) {


		// java.sql 패키지에 저장된 Date 클래스
		// Oracle Database에 저장할 때 사용한다.

		// 참고
		// DB는 날짜 형식은 "-", "/"로 나타냄
		// 자바에서 저 형식에 맞게 모양을 바꾼 날짜임.

		Date now = new Date(System.currentTimeMillis()); // 자동완성 목록에서 작대기 그어져 있는 것 ->지금은 지원하지만 나중에 지원 안한다는 것 -> 쓰지 말라는 것임   
		//                 ↑ long 타입의 날짜를 원한다는 것 -> timestamp를 쓴다는 것임.
		//                   timestamp 값 매개변수를 필요로 함.
		System.out.println(now);
	}

}
