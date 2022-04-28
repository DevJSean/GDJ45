package ex01_ramdom;

public class MathRandom {

	public static void main(String[] args) {
		
		// Math 클래스에 들어가 있는 Random 메소드
		
		System.out.println(Math.random());      
		// static이기 때문에 클래스 이름으로 호출 가능한 것. double 타입이 나옴 
		// 0.0 <= Math.ramdom() <1.0
	
		// 1. 확률 처리를 할 수 있다. 발생하는 난수 값이 0% ~ 100% 사이의 한 확률
		// 10%(0.1) 확률로 "대박", 나머지 확률로 "꽝" 처리
		if(Math.random() < 0.1)
			System.out.println("대박!");
		else
			System.out.println("이런~");
		
		// 2. 정수형 난수로 변환
		// Math.random()				 0.0 <= 난수 < 1.0
		// Math.random() * 5			 0.0 <= 난수 < 5.0
		// (int)(Math.random() * 5)	       0 <= 난수 < 5
		//					     ↑ 발생시킬 난수의 종류 수 (0, 1, 2, 3, 4)
		// (int)(Math.random() * 5) + 1    1 <= 난수 < 6   (1, 2, 3, 4, 5)
		
		System.out.println((int)(Math.random() * 5) + 1);
		
		// 문제: 주사위 3개 던지기
		for(int i = 0; i < 3; i++) {
			System.out.println((i + 1) + "번째 주사위 : " + ((int)(Math.random() * 6) + 1)); // +1 먼저 계산하도록 괄호 주의
		}

	
		// 문제: 6자리 인증번호 만들기
		String code = "";
		for(int i = 0; i < 6; i++)
			code += (int)(Math.random() * 10); // 끝에 + 0 들어가야되는데 생략 가능하니까..
		System.out.println("전송된 인증번호는 " + code + "입니다.");  // String이니까 맨 앞자리가 0 나올 수 있음
	
	
	
	
	
	}

}
