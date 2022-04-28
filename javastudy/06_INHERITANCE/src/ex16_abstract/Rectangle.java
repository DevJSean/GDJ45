package ex16_abstract;

public class Rectangle extends Shape {

	// field
	private int width;
	private int height;
	
	// constructor
	public Rectangle(String name, int width, int height) {
		super(name);
		this.width = width;
		this.height = height;
	}
	
	// method
	// Ctrl + space 누르면 제일 먼저 해야하는 오버라이드가 나옴
	// 혹은 우클릭 source 메뉴 Override/implement Methods... 누르면 필수로 해야하는 오버라이드가 체크되어 있음.
	@Override
	public double getArea() {
		return width * height;
	}
	
	
}
