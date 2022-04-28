package ex14_downcasting;

public class Main {

	public static void main(String[] args) {

		Person p = new Alba();
		
		p.eat(); // 원래는 Person 클래스의 메소드만 사용 가능.
		
		// downcasting
		// 부모 타입의 인스턴스(p)를 잠시 자식 타입으로 바꿔주는 것.
		((Alba) p).study();
		((Alba) p).work();
		
		
		
		// 권장!!
		// 잘못된 casting 형변환을 막아주기 위해 if문을 사용하는 것이 바람직하다.
		// p가 Alba의 인스턴스가 맞는지 점검하는 것
		if(p instanceof Alba) {  
			((Alba) p).study();
			((Alba) p).work();
			// p. 만 누르면 study메소드가 나온다.
		}
		
	}

}
