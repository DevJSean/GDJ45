package ex09_override;

// Square는 Shape의 후손.
// Square is a Rectangle.
// Rectangle is a Shape.

public class Square extends Rectangle{ 

	// field

	// constructor
	public Square(String type, int n) {           // Rectangle과 내용이 같기 때문에
		super(type, n, n);
	}
	
	// method

	
}
