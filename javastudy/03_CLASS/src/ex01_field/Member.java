package ex01_field;

public class Member {

// main method가 없다. 클래스의 필드 선언과 메소드 선언할 때는 메인메소드 안쓴다.
// 클래스를 구성하는 것은 필드라는 값. 그리고 메소드라는 기능이다.
	

// 클래스 Member
// Member라는 타입(byte, short, int, double 등..)이 사용자에 의해서 새로 생성된다는 뜻
	
	// 필드 (field)  회원 한명이 다섯개 필드를 가지게 된다는 뜻. 회원 한명 당 다섯개 데이터
	// 1. 클래스가 가지는 값이다.
	// 2. 일반 변수와 달리 자동으로 초기화된다.
					// 초기화 되는 값들
	String userId;  // null : 아무것도 가지고 있지 않은 값
	String name;	// null
	int age;		// 0    : 필드들은 이미 0으로 초기화 되기 때문에 변수선언처럼 0값을 넣을 필요가 없다.
	double height;	// 0.0
	boolean isVIP; 	// false : 거짓 값으로 초기화 됨.
	
}
