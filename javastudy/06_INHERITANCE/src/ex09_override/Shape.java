package ex09_override;

public class Shape {

	// field
	private String type;
	
	// constructor
	public Shape(String type) {
		this.type = type;
	}
	
	// method
	public double getArea() {
		return 0;  // 넓이 기본 값 0 설정
	}
	
	public void info() {
		System.out.println("도형 타입 : " + type);
	}
	
	
	
}
