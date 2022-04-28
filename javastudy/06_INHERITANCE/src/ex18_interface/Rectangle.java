package ex18_interface;

// 인터페이스 Shape을 상속 받는다. (X)
// 인터페이스 Shape을 구현한다. (O)   표현만 다른 것이다.

//                      ↓↓↓↓↓↓↓
public class Rectangle implements Shape {

	// field
	private int width;
	private int height;

	
	// constructor
	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	// 인터페이스의 추상 메소드를 반드시 구현해야 한다.
	// source 메뉴의 Override/Implement methods..를 사용한다.
	// method
	@Override
	public double getArea() {
		return width * height;
	}


	@Override
	public void info() {
		System.out.println("너비" + width + "높이" + height + "넓이" + getArea());		
	}
}
