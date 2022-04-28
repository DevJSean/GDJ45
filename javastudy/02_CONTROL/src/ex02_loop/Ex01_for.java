package ex02_loop;

public class Ex01_for {
	public static void main(String[] args) {
		
		// for
		// 1. 특정 실행문을 여러 번 반복하는 경우에 사용한다.
		// 2. 형식
		//	  for(초기화; 조건식; 증감식)
		//		  실행문
		//    for(   1  ;   2   ;   4   )
		//			 3
		//    1 -> 2 -> 3 -> 4 -> 2 -> 3 -> 4 -> 2 ... 반복
		
		// 1부터 10까지 출력
		for(int n = 1; n <= 10; n++)
			System.out.println(n);
		
		// 10부터 1까지 거꾸로 출력
		for(int n = 10; n >= 1; n--)
			System.out.println(n);
		
		// 문제 : 구구단 7단을 모두 출력하기
		for(int n = 1; n <=9; n++)
			System.out.println("7 X " + n +" = " + n*7);
		
		int dan = 7;  // 따로 변수를 지정하는 이유는 8단, 5단 등 다른 구구단이 필요할 때 수정을 최소화하기 위함
		for(int n = 1; n <= 9; n++)
			System.out.println(dan + " X " + n + " = " + dan * n); // 연산의 우선순위 우측의 곱하기 먼저
		
		// 문제 : 1부터 100 사이의 모든 정수를 다 더하기
		int hap = 0;
		for(int n = 1; n <= 100; n++)
			hap +=n;
		System.out.println(hap);
			
		// 문제 : 1부터 100 사이의 모든 3의 배수를 출력하기
		for(int n = 1; n <= 100; n++)
			if(n % 3 == 0)
				System.out.println(n);
		
		// 문제 : begin부터 end까지 모두 더하기
		// 단, begin이 end보다 작다는 보장은 없다.
		int begin = 1;
		int end = 10;
		int hap1 = 0;
		// 만약, begin > end이면 begin과 end를 교환해서 처리.
		int temp; //temporary
		if(begin > end) {
			temp = begin;
			begin = end;
			end = temp;
		}
		for(int n = begin; n <= end; n++)
			hap1 += n;
			System.out.println(begin + "부터 " + end + "까지 합은 " + hap1 + "입니다.");
			
		
			
			
			
			
		
		}
}
