package ex5_method_overloading;

public class Calculator {

	// method
	
	// 메소드 오버로딩
	// 1. 같은 이름의 메소드를 2개 이상 만들 수 있다. (add로 3개나 만듦)
	// 2. 메소드 명이 같을 때는, 반드시 매개변수를 다르게 설정해야 한다.
	// 3. 반환타입(void)은 전혀 상관 없다. 같거나 다르거나 상관이 없다.
	
	// 자주 사용했던 println도 오버로딩이다. print. 쳐보면 반환타입이 다른게 잔뜩 있다.
	
	public void add(int a, int b) {
		System.out.println(a + "+" + b + "=" + (a + b)); //반드시 괄호 있어야 함
	}
	
	public void add(int a, int b, int c) {
		System.out.println(a + "+" + b + "+" + c + "=" + (a + b + c));
	}
	
	public void add(double a, double b) {
		System.out.println(a + "+" + b + "=" + (a + b));
	}
}
