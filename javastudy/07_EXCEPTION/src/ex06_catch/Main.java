package ex06_catch;

public class Main {

	public static void m1() {
		// 예외 클래스를 알아내기
		
		try {
			int[] arr = new int[3];
			arr[0] = 1;
			arr[1] = 1;
			arr[2] = 1;
			arr[3] = 1;
			
		} catch(Exception e) { 
			// 예외 클래스 이름 알아내기
			String className = e.getClass().getName(); //	getClass 어떤 클래스가 전달되었는지 가져오고 (getName) String 타입으로 가져와라
			System.out.println(className);
			
			// 예외 메세지 알아내기, 왜 예외가 발생했는지
			String message = e.getMessage();
			System.out.println(message);
			
		}
	}
	
	
	public static void m2() {
		// 예외 메시지는 직접 만드는 것이 좋다.
		// 개발자들이 예외 사유를 정리해서 해당 메세지를 사용하는 경우
		// 개발자들의 주된 사용 방법임.
		try {
			
			int score = 140;
			if(score < 0 || score > 100)
				throw new RuntimeException("점수는 0~100 사이 정수입니다."); //예외 메세지를 직접 만들 떄 RunimeException 많이 쓴다
			System.out.println("점수는 " + score + " 점입니다.");
			
		} catch (RuntimeException e) {
			System.out.println(e.getMessage()); // 이 메세지가 곧 위의 직접 지정한 메세지가 된다.
		}
	}
	
	public static void m3() {
		// 예외가 발생했을 때, 왜 예외가 발생했는지 사유를 알아내고 수정할 수 있어야 함.
		// 개발하는 과정 중에 넣는 예외 처리 코드는 예외가 어디서 발생했는지 예외 확인 코드이다.
		// 메세지 출력하는 위와 같은 것은 사용자를 위한 서비스 같은 것.
		// 앞으로 예외 처리할 때 특별한 일 아니면 대부분 이것을 사용한다.
		
		try {
			Integer.parseInt("1.5"); //NumberFormatException이 발생할 것.
		} catch(Exception e) {
			e.printStackTrace(); // 발생한 예외를 하나씩 추적하는 예외 확인 코드, 추적된 내용을 알아서 print해준다.
		}
	}
	
	
	public static void main(String[] args) {

		//m1();
		//m2();
		m3();
		
		
	}

}
