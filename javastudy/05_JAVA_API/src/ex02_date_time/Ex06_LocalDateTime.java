package ex02_date_time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex06_LocalDateTime {

	public static void main(String[] args) {
		
		// LocalDateTime 클래스
		// 1. java.time 패키지에 저장된  클래스이다.
		// 2. JDK 1.8 (8버전) 이후 지원되는 최신 날짜/시간 처리 클래스
		// 3. 특정 요소를 사용할 수 있고, pattern도 지정할 수 있다.
		
		//Local만 치고 Ctrl +  Space 누르면 나옴
		LocalDateTime now = LocalDateTime.now(); // static 메소드는 new 안쓰고 클래스 이름으로 접근한다.
		
		int year = now.getYear();   // Calendar와 다르게 메소드가 따로 존재한다
 		int month = now.getMonthValue();  // 1 ~ 12, Calendar와 다르게 +1 을 따로 하지 않아도 된다.
 		int day = now.getDayOfMonth();
 		
 		System.out.println(year);
 		System.out.println(month);
 		System.out.println(day);
 				
 		
 		int hour = now.getHour();
 		int minute = now.getMinute();
 		int second = now.getSecond();
 		int nanoSecond = now.getNano();
 		
 		System.out.println(hour);
 		System.out.println(minute);
 		System.out.println(second);
 		System.out.println(nanoSecond);
		
		// 패턴 적용하는 방법 DateTimeFormatter 클래스를 따로 씀
 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a h:mm yyyy-MM-dd");
 		String res = dtf.format(now);
 		System.out.println(res);
		
		

	}

}
