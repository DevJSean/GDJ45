package ex02_try_catch;
 
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void m1() {
		// NullPointerException 처리
		try {
			String[] hobbies = new String[3];
			for(String hobby : hobbies) {
				System.out.println(hobby.substring(0, 2)); //substring은 그냥 해보는 것.여기서 중요하지 않음
			}
			System.out.println("취미 출력이 끝났습니다."); // 실행되지 않음. 왜냐하면 try 블록에서 예외가 발생하는 지점에서 
														   // catch의 예외처리 매개변수에 전달하고 더 이상 처리하지 않기 때문.
		} catch(NullPointerException e) {  // 예외처리 매개변수는 e로 짓는게 통상적인 규칙이다.
			System.out.println("NullPointerException 발생");
		}
		
	}
	
	public static void m2() {
		// NumberFormatException 처리               (외부로부터)읽어들인 데이터를 가공하다가 종종 발생하는 Exception이다.
		try {
			String input = "20, 21, 22, 23.5, 24"; 		// 23.5는 잘못 들어간 데이터
			String[] inputs = input.split(", ");		// split : 문자열을 설정하는 기준마다 분리시키는 메소드. 주의! "."을 기준으로 나눌 순 없다.
			int[] ages = new int[inputs.length];
			for(int i = 0; i < inputs.length; i++) {
				ages[i] = Integer.parseInt(inputs[i]);      // 23.5에서 NumberFormatException 발생.
				System.out.println((i + 1) + "번째 입력 나이 " + ages[i]);
			}
		} catch(NumberFormatException e) {
			System.out.println("입력은 모두 정수여야 합니다."); 
			
		}
		
	}
	
	public static void m3() {
		// catch 블록을 여러 개 만들어서 Exception 처리
		// Scanner 인스턴스를 이용해서 2개의 정수를 입력 받는다.
		// +, -, *, /, % 연산 결과를 출력한다.
		// 1. 두 번째 정수 입력으로 0을 입력하면 나누기를 수행할 때 ArithmeticException 발생.
		// 2. 정수가 아닌 것을 입력하면 java.util.InputMismatchException 뱔생

		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("정수1 >>> ");
			int a = sc.nextInt();
			System.out.print("정수2 >>> ");
			int b = sc.nextInt();
			System.out.println(a + "+" + b + "=" + (a + b));
			System.out.println(a + "-" + b + "=" + (a - b));
			System.out.println(a + "*" + b + "=" + (a * b));
			System.out.println(a + "/" + b + "=" + (a / b));
			System.out.println(a + "%" + b + "=" + (a % b));
			sc.close();
			
		} catch(ArithmeticException e) {
			System.out.println("정수2는 0일 수가 없습니다.");
		} catch(InputMismatchException e) {  // import 필요하다
			System.out.println("정수만 입력할 수 있습니다.");
		}
		
	}
	
	public static void m4() {
		// 모든 예외는 Exception 클래스로 처리할 수 있다.
		
		try {
			int[] arr = new int[3];
			arr[0] = 100;
			arr[1] = 200;
			arr[2] = 300;
			arr[3] = 400; // 존재하지 않는 요소
		} catch(Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
	}
	
	public static void main(String[] args) {

		// m1();
		// m2();
		// m3();
		// m4();
		
	}

}
