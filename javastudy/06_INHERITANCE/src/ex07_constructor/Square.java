package ex07_constructor;

// 정사각형은 사각형이다.
// Square is a Rectangle.

public class Square extends Rectangle {



	//fields
	/*  
	  Rectangle을 상속 받고 있고 필요한 필드는 너비와 높이로 중복이니까
	  필드를 새로 선언할 필요가 없다.
	*/
	
	//constructor
	// 필드를 선언하지 않았으니 Generate Constructors from Superclass를 사용해야 한다.
	/* 
	public Square(double width, double height) {
		super(width, height);    사용하면 이런식으로 나오는데, 정사각형은 너비와 높이가 같으므로 수정한다.
		
	}
	*/
	// Main에서 매개변수 3을 n으로 전달, Rectangle을 호출하여 n값을 int width, int height로 전달
	public Square(double n) {
		super(n, n);  // Rectangle 호출		
	}
	
	
	// method
	/*
	   Rectangle 클래스의 info 메소드를 그대로 사용한다.
	   info() 메소드를 새로 추가할 필요가 없다.
	*/
	
	
	
}
