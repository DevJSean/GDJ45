package ex02_loop;

public class Ex02_while {

	public static void main(String[] args) {
		
		// while
		// 1. 특정 실행문을 여러 번 반복하는 경우에 사용한다.
		// 2. 언제 끝나는지 강조되는 경우에 사용한다.
		// 3. 반복의 횟수가 정해지지 않은 경우에 사용한다.
		// 4. 형식
		//	  while(조건식)
		//		  실행문(많으면 {}중괄호로 묶기)
			
		// 통장에서 돈 빼기
		// 한 번에 300원씩 뺄 수 있을 때가지 빼기
		// 잔고가 얼마인지 출력
		
		int account = 3500;
		int money = 300;
		while(account >= money)
			account -= money;
		System.out.println("최종잔액: " + account + "원");
		
		int account1 = 3500;
		while(account1 >= 300)
			account1 -= 300;
		System.out.println("최종잔액: " + account1 + "원");
		
		// 대부분은 for문 사용하지만 정해진 특정 경우에는 while문을 사용해야 한다.
		
		
	}
}
