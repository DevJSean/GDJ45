package ex05_constructor;

public class Person {

	// field
	private String name;

	// constructor
	// 슈퍼 클래스의 생성자가 디폴트 형식이 아니기 때문에
	// 서브 클래스의 생성자에서는 반드시 Person의 생성자 호출이 필요하다.
	//                    ↓ 매개변수가 존재
	public Person(String name) {
		super(); // 지금 이 super는 없어도 됨.
		this.name = name;
	}
	
	



	// method
	public void eat() {
		System.out.println(name + "이(가) 먹는다.");
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
