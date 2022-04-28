package ex02_date_time;

import java.util.Calendar;

// 자동으로 import 하는 방법 : 윗 줄에서 Ctrl + SHIFT + O   
// 혹은 밑의 Calendar 클래스 뒤에서 Ctrl+space bar로 직접 import

// 자바의 모든 클래스들은 특정 패키지 소속이다.
// 그 중 java.lang 패키지(자바랭)에 포함된 건 기본 클래스라고 한다.
// java.lang 패키지에 소속된 클래스들은 import가 필요 없다.
// java.lang 패키지 소속 클래스
// : System, String, Integer, Long, Double, Math 등



public class Ex02_Calendar {

	public static void main(String[] args) {


		// java.util 패키지에 저장된 Calendar 클래스
		// calendar 인스턴스는 현재 날짜와 시간을 저장하고 있다.
		Calendar calendar = Calendar.getInstance();  // 자동으로 import되지 않으면  Calendar 클래스 뒤에서 Ctrl+space bar로 import
		// 클래스를 입력할 때 반드시 Ctrl+spacebar 이용한다.

		// Calendar는 특정/시간 요소가 필요할 때 사용한다.
		// 년 월 일은 사용할 일이 많다.
		int year = calendar.get(Calendar.YEAR); // 필드 값이 상수값(Calender.YEAR : 1)임. Ctrl 누른 채로 YEAR 누르면 Calendar.class의 요소를 보여줌
		int month = calendar.get(Calendar.MONTH) + 1 ;  // month 값을 제대로 쓰려면 +1일 해야 함.... (월 : 0~11로 저장 되어있음)
		int day = calendar.get(Calendar.DAY_OF_MONTH); //그냥 day 말고 day of month
		
		// 요일은 세트로 씀
		int weekNo = calendar.get(Calendar.DAY_OF_WEEK); // 일요일: 1, 월요일: 2, ... 토요일: 7
		String[] weekNames = {"", "일", "월", "화", "수", "목", "금", "토"};
		//                         ↑weekNames[1]
		
		System.out.println(year + "년" + month + "월" + day + "일" + weekNames[weekNo] + "요일");
		
		// 시간 추출
		int ap = calendar.get(Calendar.AM_PM); 		 // 오전: 0, 오후: 1
		int hour12 = calendar.get(Calendar.HOUR); 		 // 1 ~ 12
		int hour24 = calendar.get(Calendar.HOUR_OF_DAY); // 0 ~ 23
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		String[] ampm = {"오전", "오후"};
		System.out.println(ampm[ap] + " " + hour12 + "시 " + minute  + "분 " + second + "초");
		System.out.println(hour24 + "시 " + minute + "분 " + second + "초");
	
		// timestamp
		long timestamp = calendar.getTimeInMillis();
		System.out.println(timestamp);
	
	
	}
}
