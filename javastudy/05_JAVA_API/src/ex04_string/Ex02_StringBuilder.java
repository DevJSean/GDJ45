package ex04_string;

// String을 만들어주는 클래스

public class Ex02_StringBuilder {

	public static void main(String[] args) {
		
		// 1. String 클래스의   + 연산자 이용  (여태까지 한 방법)
		// 2. StringBuilder 클래스의 append 메소드  (java에서 성능 때문에 더 권장하는 방법, java.lang 기본 패키지에 있어서 import 필요 없다)
		
		// 1번 방법
		String str1 = "";
		for(int i = 0; i < 6; i++) // 6번의 반복: 6자리 만들기
			str1 = str1 + (int)(Math.random() * 10);  // 0~9까지의 10개의 랜덤
			// str1 += (int)(Math.random() * 10);  동일한 내용
		System.out.println(str1);
		
		// 2번 방법
		StringBuilder sb = new StringBuilder();   // sb는 StringBuilder 타입(클래스)임 그래서 sb는 String처럼 사용할 수는 없다
		for(int i = 0; i < 6; i++)
			sb.append((int)(Math.random() * 10)); // append메소드 이용
		// sb는 String으로 바꿔서 사용해야 한다.
		String str2 = sb.toString();
		System.out.println(str2);
		
		
		// 문제: 아래 출력물을 StringBuilder로 만들기
		// 1 2 3 4 5 6 7 8 9 10
		StringBuilder sb2 = new StringBuilder();
		for(int n = 1; n <= 10; n++)
			sb2.append(n).append(" "); // 공백 만들기 위해 append 메소드 뒤에 한번 더 이어붙인다. 메소드 이어 붙이기 : 메소드 체이닝
		String str3 = sb2.toString();
		System.out.println(str3);
		
		
		
		
		
		
		
		
		

	}

}
