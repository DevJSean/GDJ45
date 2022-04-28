package ex16_abstract;

public class Circle extends Shape {

	// field
	private double radius;
	
	// constructor
	public Circle(String name, double radius) {
		super(name);
		this.radius = radius;
	}

	// method
	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	
	
}
