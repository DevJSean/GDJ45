package ex03_arithmetic;

public class Ex03_operator {

	public static void main(String[] args) {
		
		// 대입 연산 (전달, 저장)
		// = : 등호의 오른쪽 값을 왼쪽 변수에 넣는다

		int a = 10;
		
		a = 20;  //덮어쓰기
		System.out.println(a);
		
		// 20 = a; 불가능, 왼쪽이 변수여야 함
		
		
		// 유명한 대입 예제
		// 변수에 저장된 값을 교환하시오, x와 y에 무엇이 들어있는지 모른다는 과정

		int x = 1;
		int y = 2;
		
		int t = x; // t, temporary(임시)
		x = y;
		y = t;
	
		System.out.println(x);
		System.out.println(y);
		
	}

}
