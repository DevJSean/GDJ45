package prac1;

public class Watch {
	
	// field
	private int hour;    // 0 ~ 23
	private int minute;  // 0 ~ 59
	private int second;  // 0 ~ 59
	
	// constructor
	public Watch(int hour, int minute, int second) {     //source메뉴 generate constructor using field...
		this.hour = hour;                 				 // 각각의 매개변수들을 필드 값으로 전달하기
		this.minute = minute;
		this.second = second;
	}
	
	// method
	public void addHour(int hour) {
		// this.hour : 16시
		// hour : 25시
		// this.hour + hour : 41시
		// (this.hour + hour ) % 24 : 17시
		// 24시각제는 0~23까지만 세니까 24시가 될 떄마다 0으로 바꾸는 작업 필요. 
		// 이를 24로 나눠서 나오는 나머지로 계산. 모듈러 연산이라고 함.
		if (hour < 0) {
			return;  // addHour 메소드 종료
		}
		this.hour += hour;
		this.hour %= 24;
	}
	
	public void addMinute(int minute) {
		if (minute < 0) {
			return;
		}
		this.minute += minute; // 원래 필드로 가지고 있던 15분과 전달된 61분을 더해 76분이 됨.
		// 시간: this.minute / 60
		// 분:   this.minute % 60
		addHour(this.minute / 60);  // 1시간을 시간에 추가해줌.
		this.minute %= 60;  // 모듈러 연산.  16분을 갱신
	}
	
	public void addSecond(int second) {
		if (second < 0) {
			return;
		}
		this.second += second; // 30초 + 3661초 : 3691초(this.second값)
		// 분 : this.second / 60
		// 초 : this.second % 60
		addMinute(this.second / 60);  //3691초 / 60초 : 61분
		this.second %= 60; // 31초
	}
	
	public void see() {
		System.out.println(hour + "시 " + minute + "분 " + second + "초");
	}
	
}