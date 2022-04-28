package ex18_interface;

public class Circle implements Shape {

	// field
	private double radius;

	// constructor
	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	// method
	@Override
	public double getArea() {
		return PI * Math.pow(radius, 2);  //PI : interface Shape에 선언한 상수값
	}

	@Override
	public void info() {
		System.out.println("반지름" + radius + "넓이" + getArea());
		
	}



}
