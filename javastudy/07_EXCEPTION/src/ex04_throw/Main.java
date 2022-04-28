package ex04_throw;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// throw문
		// 직접 예외를 발생시켜서 처리할 때 사용한다.
		// 자바는 모르지만 사용자만 아는 오류를 일부러 만들 때. 나이가 1000살.
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("나이를 입력하세요 >>> ");
			int age = sc.nextInt();
			
			// 예외를 직접 발생시킬 때 RuntimeException 주로 사용한다.
			if(age < 0 || age > 150)
			//	RuntimeException e = new RuntimeException ();
			//	throw e;
				throw new RuntimeException(); // 동일한 코드이고, 주로 이걸 쓴다. RuntimeException을 던지고 catch가 이를 잡는다.
			
			if(age < 20)
				System.out.println("미성년자입니다.");
			else
				System.out.println("성인입니다.");
		
		} catch (Exception e) {   // catch는 이제 자동완성으로 만들자
			System.out.println("예외가 발생했습니다.");
		} finally {
			sc.close();
		}
		
	}

}
