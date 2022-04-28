package ex01_exception;

import java.util.Scanner;

// 어떤 예외들이 발생하는지 알아보기

public class Main {

	public static void m1() {
		// NullPointerException : null값이 메소드를 호출할 때 발생
		
		// field
		String[] hobbies = {
				"game",
				"running",
				"swimming",
				null,		
				"reading"
		};
		for(String hobby: hobbies) {  // 향상 for문
		//	if("reading".equals(hobby)) 이렇게 바꾸면 예외가 발생하지 않는다. null이 메소드를 호출하지 않으니까.
			if(hobby.equals("reading"))  // 여기서 NullPointerException이 발생함. hobby에 null값이 전달됐을 때, if(null.equals("reading"))이 동작함.
				System.out.println("저와 취미가 같군요");											// null은 어떤 메소드도 호출할 수 없다.
		}
	}
	
	
	public static void m2() {
		// NumberFormatException : String을 Number 타입으로 변환할 때 발생
		Scanner sc = new Scanner(System.in);
		System.out.print("이름(필수) >>> ");
		String name = sc.nextLine();
		System.out.print("나이(선택) >>> ");
		String strAge = sc.nextLine();
		/*         이렇게 해결해야 한다.
		int age;
		if(strAge.isEmpty()) // isEmpty 비어 있는 값인지 알아내는 메소드
			age = 0;
		else
			age = Integer.parseInt(strAge);
		*/
		int age = Integer.parseInt(strAge); // 문자열을 정수로 바꾸기
		System.out.println("이름 " + name + " 나이 " + age + "살");
		sc.close();
		
		/*   이렇게 나이를 입력을 하지 않으면(비어있는 문자열) NumberFormatException이 발생한다
		이름(필수) >>> kim
		나이(선택) >>>           비어있는 문자열을 Integer.parseInt("")를 통해 정수로 바꾸려다보니 예외가 발생한다.
		
		나이(선택) >>> twenty    이렇게 정수로 바꿀 수 없는 문자열도 동일한 예외가 발생한다.
		*/
	}

	
	
	public static void main(String[] args) {

		m2();
		
		
		
	}

}
