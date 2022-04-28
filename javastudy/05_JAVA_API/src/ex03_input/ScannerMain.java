package ex03_input;

import java.util.Scanner;

public class ScannerMain {

	public static void main(String[] args) {


		// java.util 패키지에 저장된 Scanner 클래스
		
		Scanner sc = new Scanner(System.in); // System.in(키보드)은 System.out(모니터)의 반대

		// 입력 메소드
		// 1. int	 : nextInt()
		// 2. long	 : nextLong()
		// 3. double : nextDouble()
		// 4. String
		//    1) nextLine(): Enter키를 입력의 끝으로 본다.(공백(띄어쓰기) 입력 가능)
		//    2) next(): 공백문자(Enter, Space 등)를 입력의 끝으로 본다.(공백 입력 불가능)
		
		System.out.println("이름을 입력하세요");
		String name = sc.next();  // 뽀 로 로 Enter - 이렇게 누르는데 뽀로로만 가져감.
		
		sc.nextLine(); 		// 엔터 남는거 해결
		
		
		System.out.println("주소를 입력하세요");
		String address = sc.nextLine(); //위에서 안가져간 Enter가 입력됨. nextLine 메소드는 엔터키를 입력의 끝으로 봄.
		
		System.out.println("나이를 입력하세요");
		int age = sc.nextInt();
		
		System.out.println("키를 입력하세요");
		double height = sc.nextDouble();
		
		System.out.println("성별을 입력하세요");
		char gender = sc.next().charAt(0);  // .charAt 메소드는 그 문자열의 인덱스로 인덱싱. 문자열도 배열처럼 인덱스가 붙는다. 
		// char는 next메소드가 없음. 근데 문자열의 일종이니 .next()사용. 
		// 단, 한글자만 입력받는 것으로 추가작업
		
		System.out.println(name);
		System.out.println(address);
		System.out.println(age);
		System.out.println(height);
		System.out.println(gender);
		
		sc.close(); // 연결 해제. scanner처럼 String 쓰는 애들은 다 사용 후 닫아줘야함. 
					// 근데 안 하면 JVM이 스스로 처리함. 안 해도 된다는 뜻.
		
		
		
	}

}
