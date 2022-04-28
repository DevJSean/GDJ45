package ex11_upcasting;

public class Main {

	public static void main(String[] args) {
		
		Person p1 = new Student();  // 타입은 부모인데 자식을 생성함.
		Person p2 = new Alba();     // 부모의 부모 타입도 가능
		
		p1.eat();  // eat 메소드만 가능하다. 타입에 의해서 동작한다고 생각하면 된다.
		p2.eat();
		
		// 부모의 타입으로 자식을 저장할 수 있다.
		// 하지만 자식의 메소드를 사용할 수 없다.
		
		// 장점.
		// 부모(Person)을 상속 받는 모든 인스턴스를 부모(Person) 타입으로 저장할 수 있다. 
		// Person[] people = new Person[10]; 10개의 배열을 만들었다.
		// 이때, Student도 Alba도 저장할 수 있다. 여러가지를 인스턴스들을 하나의 Person 타입으로 저장할 수 있다.
		
		// 단점
		// 부모(Person) 타입으로 선언되었기 때문에 부모(Person)의 메소드만 호출할 수 있다.
		
		

		
	}

}
