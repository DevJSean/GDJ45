package ex05_string;

public class Ex01_contatenate {

	public static void main(String[] args) {
		
	
		// 문자열 연결 연산 : + 
		
		System.out.println(1 + 1);       // 2    정수
		System.out.println(1 + "1");     // "11" 문자열
		System.out.println(1 + 1 + "1"); // "21" 문자열   자바는 왼쪽부터 차례대로 계산한다 
		
		int age = 26;
		int age2 = 5;
		String res = (age >= 20 ? "성인" : "미성년자");
		String res2 = ( age2 >= 20 ? "성인": "미성년자");
		System.out.println(age + "살은 " + res + "입니다.");
		System.out.println(age2 + "살은 " + res2 + "입니다.");
		
	}

}
