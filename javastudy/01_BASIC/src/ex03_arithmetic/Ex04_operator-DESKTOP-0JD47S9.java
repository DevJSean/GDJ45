package ex03_arithmetic;

public class Ex04_operator {

	public static void main(String[] args) {
		
		// 복합 대입 연산
		// +=, -=, *=, /=, $=

		
		int wallet = 0;
		
		wallet = 30000;
		
		wallet = wallet + 30000;  //기존 wallet의 값을 30000 늘린다.
	
		wallet += 30000;          //동일한 코드

		System.out.println(wallet);
		
		
		
		// 문제. money를 5% 늘려보자.
		
		long money1 = 10000L;
		money1 = money1 + (long)(money1 * 0.05);    
		// 우변의 왼쪽 money는 long 타입 오른쪽 money는 double 
		// 정수와 실수 계산하면 실수가 된다
		// 하지만 처음 money는 long으로 선언해서 오른쪽 money를 casting해야 한다.
		System.out.println(money1);
		
		long money2 = 10000L;
		money2 += (long)(money2 * 0.05);
		System.out.println(money2);
		
		long money3 = 10000L;
		money3 *= 1.05;
		// money = money * 1.05; 는 안된다. 실수 연산 결과를 정수(long)타입에 저장할 수 없다.
		// money *= 1.05; money는 long, 1.05는 double. 연산 결과가 double로 잘 처리된다. 이 때 money는 java에 의해서 double로 처리된다. 이 시점에서의 money는 double이다.
		System.out.println(money3);
		
	}

}
