package prac1;

// 다음 main 메소드를 참고하여 Watch 클래스를 구현하시오.

public class Ex08 {

	public static void main(String[] args) {

		//생성자 호출, 
		// 같은 클래스에서는 메소드(addHour 등)를 바로 쓸 수 있지만, 다른 클래스에서는 인스턴스(w)를 만들어야 그 메소드를 사용할 수 있다.
		Watch w = new Watch(16, 15, 30);         
		
		w.addHour(25);  	// 25시간 후(1시간 후)  
		w.addMinute(61);	// 61분 후 	(1시간 1분 후)
		w.addSecond(3661);	// 3661초 후(1시간 1분 1초 후)
		
		w.see();  // 19시 17분 31초

	}

}
