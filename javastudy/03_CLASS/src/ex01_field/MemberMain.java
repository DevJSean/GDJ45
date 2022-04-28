package ex01_field;

public class MemberMain {

	public static void main(String[] args) {
		
		// Member : 클래스(타입)
		// m 	  : 인스턴스(객체) 
		Member m = new Member();  // m은 이전에 선언한 5개의 필드를 가진다. new 클래스(): 멤버를 만들어달라는 코드
		//                ↑인스턴스를 생성할 때 사용하는 생성자 메소드
		
		// 인스턴스가 가지고 있는 필드 값은 마침표(.) 표기법으로 확인할 수 있다.
		System.out.println(m);       // 클래스명@참조값
		System.out.println(m.userId);
		System.out.println(m.name);
		System.out.println(m.isVIP);
		System.out.println(m.age);
		System.out.println(m.height);
		
		
		//인스턴스에 값을 전달하기
		m.userId = "manager";
		m.name = "관리자";
		m.age = 45;
		m.height = 180.5;
		m.isVIP = true;
		System.out.println(m.userId);
		System.out.println(m.name);
		System.out.println(m.isVIP);
		System.out.println(m.age);
		System.out.println(m.height);
	}
}
