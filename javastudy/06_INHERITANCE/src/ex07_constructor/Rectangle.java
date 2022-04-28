package ex07_constructor;

public class Rectangle {

	// field
	private double width;  //너비
	private double height; //높이
//  private double area; 넓이를 따로 필드 설정 하지 않아도 된다. 계산할 수 있는 항목이므로 필드로 둘 필요가 없다.
	
	//constructor
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	//method	
	public double getArea() { //넓이 계산하는 메소드 따로 만든다.
		return width * height;
	}
	
	public void info() {
		System.out.println("너비 " + width + ", 높이 " + height + ", 넓이 " + getArea()); // 같은 클래스에서는 메소드 호출 이름만 적으면 된다.
	}



	
	
	
	
	
	
	
	
	
	
}
