package ex03_finally;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// finally 블록
		// 항상 마지막에 반드시 실행되는 블록
		
		Scanner sc= new Scanner(System.in);;  // try 블록에서 선언하지 말고 Main에서 선언해야 finally 블록에서도 sc를 사용할 수 있다.
		
		try {
	
			System.out.print("나이를 입력하세요 >>> ");
			int age = sc.nextInt();
			
			if(age < 20)
				System.out.println("술 담배 판매 금지");
			else
				System.out.println("술 담배 판매 가능");
			
			// sc.close();  // 여기서 close하면 위에서 예외가 발생했을 때 처리되지 않는다.
			
		} catch(Exception e) {
			System.out.println("예외가 발생했습니다.");
		} finally {
			sc.close(); // finally 블록은 주로 자원의 반납을 처리한다. 
		}
		
	}

}
