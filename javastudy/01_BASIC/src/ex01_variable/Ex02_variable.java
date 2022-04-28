package ex01_variable;

public class Ex02_variable {

	public static void main(String[] args) {
		//지우고 싶은 부분을 선택하고 Ctrl+D 누르면 그 줄이 지워짐
		

		// 변수(variable)
		//   1) 컴퓨터의 메모리(램)에 확보되는 데이터 저장 공간
		//   2) 메모리(램)은 1바이트마다 고유번호(0부터 시작하는 16진수: 0, 1, ..., 9 , A, B, C, D, E, F, 10, 11, 12, ... , 1F, 20, ...)를 부여
		//   3) 주소(address) : 1바이트마다 부여된 메모리의 고유번호
		//   4) 영문 한 글자 : 1 바이트, 한문 한 글자 : 2~3 바이트
		
		// primitive type(다 소문자) : byte, short, int, long, float, double, char, boolean, array
		// 클래스라서 앞글자가 대문자임 : String
		 
		// 1. byte 타입 
		//   1) 1바이트 크기
		//   2) 정수(소숫점이 없는 숫자)
		//   3) 정수를 저장하기 위함이 아닌 모든 데이터의 최소 단위로 사용
		 	
		// 2. short 타입
		//	  1) 2바이트 크기
		//   2) 정수
	    //   3) 과거용(지금은 안쓰는 타입이다)

		// 3. int 타입
	    //   1) 4바이트 크기
		//   2) 정수 (기본적인 정수의 단위, 정수를 사용할 때 int를 씀)
		//   3) 가용범위 : -2,147,483,648 ~ -2,147,483,647 (-21억 ~ + 21억)
		int myAge = 26;
		System.out.println(myAge);
			
		// 4. long 타입
		//	  1) 8바이트 크기
		//	  2) 정수
		//	  3) suffix(접미사, 뒤에 붙이는) 값이 필요하다.  suffix가 L(l)
		//	  3) 무한대는 아니지만 거의 그 정도로 크다고 생각
		//	  4) 타임 스탬프 값: 컴퓨터는 시간을 숫자로 천분의 1초 단위로 계속 늘리는 방식으로 관리한다. 타임 스탬프는 long 타입
		long myMoney = 50000L;
		System.out.println(myMoney);
		
		//   3(int), 4(long) 중요하다

		// 1바이트 = 8비트
		//          개수       가용범위
		// 1비트    2          0 ~ 1
		// 2비트    4          0 ~ 3
		// 3비트    8          0 ~ 7
		// n비트    2의 n제곱  -(2의 n-1제곱) ~ (2의 n-1제곱) -1
		
		// 5. float 타입
		//   1) 4바이트
		//	 2) 실수
		//   3) suffix(접미사)가 F(f), short 타입처럼 과거용(안쓰는  타입이다)
		
		// 6. double 타입
		//	 1) 8바이트
		//   2) 실수 (기본 단위, 실수는 double 쓰면 된다)
		//   3) 가용범위는 몰라도 된다
		double myWeight = 66.5;
		System.out.println(myWeight);
		
		// 7. char 타입 (캐릭터 타입, 문자 타입)
		//   1) 2바이트
		//   2) 문자
		//   3) 문자는 실제로 저장될 때 정수(문자의 코드값)로 처리된다
		
		//   문자        코드값
		//   '0'           48
		//   '1'           49
		//   'A'           65
		//   'a'           97
		
		char myGender = 'M';
		System.out.println(myGender);
		System.out.println((int)myGender);
		
		// 8. boolean 타입
		//   1) true, false 중 하나만 저장
		//   2) 논리 타입
		//   3) 크기 의미 없다
		boolean isKorean =  true;
		System.out.println(isKorean);
		
		// 9. String 클래스 타입(첫글자가 대문자이니 클래스)
		//   1) 기본 타입(primitive type, 1번~8번 타입)이 아니다
		//   2) 문자열
		//   3) 참조 타입(reference type) : 데이터가 아닌 데이터의 참조(주소) 값을 저장
		String myName = "SEAN";
		System.out.println(myName);
		
		
		// 정수 : int, long(L)
		// 실수 : double
		// 문자열 : String
		// 논리 : boolean
		
	}

}
