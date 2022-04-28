package ex01_branch;

public class Ex04_switch {

	public static void main(String[] args) {
		
		// switch
		// 1. 표현식의 결과 값에 따른 분기를 처리한다.
		// 2. 표현식의 결과는 int, char, String이 가능하다.
		// 3. 표현식의 결과는 long, double, boolean이 불가능하다.

		// 짝수, 홀수
		int n1 = 4;
		switch(n1 % 2) {           // case는 진입 시점 같은 것이다.
		case 0:  // n % 2의 결과가 0이면 여기서부터 실행하시오 ---- break가 필요하다는 뜻
			System.out.println("짝수"); 
		case 1:  // n % 2의 결과가 1이면 여기서부터 실행하시오.
			System.out.println("홀수");
		}
		
		int n2 = 4;
		switch(n2 % 2) {           
		case 0:  
			System.out.println("짝수"); 
			break; //switch문을 종료하시오.
		case 1:  
			System.out.println("홀수");
		}
		
		
		 // 3의 배수, 3의 배수 아님
		int n3 = 4;
		switch(n3 % 3) {
		case 0:
			System.out.println("3의 배수");
			break;
		case 1:
		case 2:
			System.out.println("3의 배수 아님");
		}
		
		// 문제: 점수에 따른 학점 출력
		int score = 78;
		switch(score / 10) {
		case 10:
			System.out.println(score + "점은 만점");
			break;
		case 9:
			System.out.println(score + "점은 A학점");
			break;
		case 8:
			System.out.println(score + "점은 B학점");
			break;
		case 7:
			System.out.println(score + "점은 C학점");
			break;
		case 6:
			System.out.println(score + "점은 D학점");
			break;
		default:  // 지금까지의 case 이외의 모든 경우
			System.out.println(score + "점은 F학점");
		}


		// 문제 : 계절
		int month = 1;
		switch(month) {
		case 1: case 2: case 12:  // case 한줄로 적어도 됨
			System.out.println(month + "월은 겨울입니다.");
			break;
		case 3: case 4: case 5:
			System.out.println(month + "월은 봄입니다.");
			break;
		case 6: case 7: case 8:
			System.out.println(month + "월은 가을입니다.");
			break;
		case 9: case 10: case 11:
			System.out.println(month + "월은 가을입니다.");
		}
	}	

}
