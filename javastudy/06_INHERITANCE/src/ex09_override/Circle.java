package ex09_override;

public class Circle extends Shape {

	// field
	private double radius;

	// constructor
	public Circle(String type, double radius) {
		super(type);
		this.radius = radius;
	}
	
	
	// method
	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2); // r 제곱 하는 법
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println("반지름 " + radius + ", 넓이" + getArea());
		
	}

}
