package ex05_string;

public class Ex02_equal {

	public static void main(String[] args) {
		
		String name1 = "alice";
		String name2 = "jessica";
		String name3 = "alice";
		
		System.out.println(name1 == name2);   // 여기서 false는 이름이 저장된 주소값(참조값)이 다르다는 뜻
		System.out.println(name1 == name3);   // 여기서 true는 name3에 저장된 주소값(찾모값)가 name1과 같다는 뜻
		// 이름 자체를 비교한 적은 없다.
		// 그래서 문자열을 동등 비교할 때는 == 안 쓴다.
		
		//문자열의 동등 비교 String 클래스의 equals 메소드를 이용한다.
		
		
		// name1과 name2가 같은 문자열인가 비교
		System.out.println(name1.equals(name2));  
		
		
		//문제 : 남자는 왼쪽, 여자는 오른쪽
		String gender = "남자";
		String res = gender.equals("남자") ? "왼쪽" : "오른쪽";
		System.out.println(gender + "는 " + res);
		
		System.out.println(gender.equals("남자") ? "왼쪽" : "오른쪽");
		System.out.println(gender.equals("여자") ? "오른쪽" : "왼쪽");
	}
	
}
