package ex02_loop;

public class Ex04_break {

	public static void main(String[] args) {

		// break
		// 1. switch문을 종료할 때 사용한다.
		// 2. 반복문(for, while)을 종료할 때 사용한다.

		// 모금 목표 : 10000
		// 13원씩 모금
		
		int total = 0;
		int money = 13;
		int count = 0;
		while(true) {   // 무한루프 생성, 끝없이 반복
			if(total >= 10000)
				break;
			total += money;
			count++;
		}
		System.out.println("최종모금액은 " + total + "원 입니다.");
		System.out.println("모금인원은 " + count + "명 입니다.");
		
		// 전체 구구단
		for(int dan = 2; dan <= 9; dan++) {   //dan 변수의 scope(범위)는 for문입니다. 이 변수는 for문 안에서만 사용할 수 있다.
			for(int n = 1; n <= 9; n++) {     //n 변수의 scope는 안쪽 for문까지
				System.out.println(dan + "x" + n + "=" + (dan * n));
			}
		}
		
		// 전체 구구단
		// 문제: 기존 코드를 수정하지 않고, 5X5=25까지만 출력하기
		
		int dan, n;  // 이 두 변수가 정수라는 선언을 미리 함. for문의 초기화에서 int 선언을 해줄 필요가 없다.
		outer: for(dan = 2; dan <= 9; dan++) {   // outer: 라벨
			for(n = 1; n <= 9; n++) {
				System.out.println(dan + "x" + n + "=" + (dan * n));
				if(dan==5 && n==5)
					break outer;   //라벨 명시, 바깥쪽 for문을 끝내버리는 방법
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
