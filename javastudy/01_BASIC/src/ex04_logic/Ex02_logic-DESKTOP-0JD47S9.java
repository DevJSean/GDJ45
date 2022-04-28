package ex04_logic;

public class Ex02_logic {

	public static void main(String[] args) {


		// 논리 연산
		// 1. && : 논리 AND, 2개 이상의 크기 비교에서 사용,        모든 비교 결과가 true이면 &&의 결과가 true
		// 2. || : 논리 OR,  2개 이상의 크기 비교에서 사용, 하나 이상의 비교 결과가 true이면 ||의 결과가 true
		// 3. !  : 논리 NOT, 1개의 크기 비교 결과를 반대로 변경하는 역할 (부정연산)
		
		// 자바에서는 true는 1, false는 0으로 처리한다. 값이 없는 것을 false로 처리한다.
		
		int a = 10;
		int b = 10;
		
		boolean res1 = (a == 10 && b == 10);
		boolean res2 = (a == 10 && b == 20);
		System.out.println(res1);
		System.out.println(res2);
		
		
		boolean res3 = (a == 10 || b == 10);
		boolean res4 = (a == 10 || b == 20);
		boolean res5 = (a == 20 || b == 20);
		System.out.println(res3);
		System.out.println(res4);
		System.out.println(res5);
		
		boolean res6 = !(a == 10);
		boolean res7 = !(a == 20);
		System.out.println(res6);
		System.out.println(res7);
		
		
		
		// 지금부터 신경쓰기
		boolean res8_1 = (a == 90 && ++b > 10);
		// 자바는 왼쪽부터 순서대로 본다. a==90 false 확인, &&를 만남, ++b>10을 보지 않아도 && 값이 false인 것을 알기 때문에 더 이상 연산하지 않는다.
		System.out.println(res8_1);
		System.out.println(b);  // 10 값이 나온다
		
		// 거꾸로 해보기
		boolean res8_2 = ( ++b >10 && a ==90);
		System.out.println(res8_2);
		System.out.println(b);  // 11 값이 나온다.
		
		// && 연산은 false가 발생하면 더 이상 연산을 진행하지 않는다
		
		
		boolean res9 = (a == 10 || ++b > 10);
		// a == 10 true 확인, ++b >10을 확인하지 않아도  || 값이 true인 것을 알기 때문에 더 이상 연산하지 않는다.
		System.out.println(res9);
		System.out.println(b);
		
		
		
	}

}
