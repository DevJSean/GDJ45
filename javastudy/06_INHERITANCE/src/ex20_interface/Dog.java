package ex20_interface;

// Pet 클래스를 상속 받음 : 애완동물이다.
// Walkable 인터페이스를 구현 : 산책할 수 있다.(라고 받아들이자)

public class Dog extends Pet implements Walkable{

	// constructor
	public Dog(String name) {
		super(name);
	}
	
	
}
