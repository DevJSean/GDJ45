package ex09_override;

public class Rectangle extends Shape {

	// field
	private int width;
	private int height;

	
	// constructor
	public Rectangle(String type, int width, int height) {
		super(type);
		this.width = width;
		this.height = height;
	}
	
	
	// method
	@Override
	public double getArea() {
		return width * height;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println(" 너비 " + width + ", 높이 " + height + ", 넓이 " + getArea());
	}
}
