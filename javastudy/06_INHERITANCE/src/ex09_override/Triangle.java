package ex09_override;

// Triangle is a Shape

public class Triangle extends Shape{

	// field
	private int width;
	private int height;

	
	// constructor
	public Triangle(String type, int width, int height) {
		super(type);
		this.width = width;
		this.height = height;
	}
	
	
	// method
	@Override
	public double getArea() {
		return width * height * 0.5;  // double 값으로 나오기 위해  / 2 가 아닌 * 0.5로 한다.
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println(" 너비 " + width + ", 높이 " + height + ", 넓이 " + getArea());
	}
	
}
