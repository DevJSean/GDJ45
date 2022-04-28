package ex6_static;

public class Calculator {

	
	// static
	// 1. static 요소(필드, 메소드)는 메모리에 미리 만들어진다. 여러 개 만들 필요 없이 메모리에 미리 올려둔다는 느낌
	// 2. 인스턴스 생성 이전에 사용할 수 있다.
	// 3. 1개만 만들어 진다.
	// 4. 클래스를 이용해서 호출한다.
	// 5. 클래스 변수(필드), 클래스 메소드(메소드)라고 부른다.
	
	
	// method
	
	public static void add(int a, int b) {
		System.out.println(a + "+" + b + "=" + (a + b));
	}
	public static void sub(int a, int b) {
		System.out.println(a + "-" + b + "=" + (a - b));
	}
	public static void mul(int a, int b) {
		System.out.println(a + "x" + b + "=" + (a * b));
	}
	public static void div(int a, int b) {
		System.out.println(a + "/" + b + "=" + (a / b));
	}
	public static void mod(int a, int b) {
		System.out.println(a + "%" + b + "=" + (a % b));
	}

	// 문제
	// a의 b제곱, 2의 3제곱
	// res = res * 2; 세번  -> for문 안에 res *= 2;
	public static void pow(int a, int b) {
		int res = 1;
		for(int n = 0; n < b; n++)
			res *= a;
		System.out.println(a + "의" + b + "제곱은" +  "=" + res);
	}
	// a의 절댓값
	public static void abs(int a) {
		int res = (a >= 0 ? a : -a); //조건연산자
		System.out.println(a + "의 절대값은" + res);
	}
	
}
