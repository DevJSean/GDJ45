package ex16_abstract;

// 직사각형, 원, 삼각형 등은 구체적인 도형이지만
// 도형이라고 하면 추상적인 것이다.
// 추상 클래스로 만들 수 있음.

// Shape은 추상 클래스
// new Shape() 은 이제 불가능하다. 추상적인 것을 실제로 만들 수 없다고 생각.
public abstract class Shape {
	
	// field
	private String name;
	
	//constructor
	public Shape(String name) {
		super();
		this.name = name;
	}

	// method
	
	// getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// 추상 메소드
	public abstract double getArea();
	
	
}
