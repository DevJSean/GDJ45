package ex07_exception_class;

// 예외클래스는 ~Exception으로 끝나도록 이름을 짓는 게 관용적인 규칙이다.
// Exception  클래스를 상속 받으면 예외 클래스가 된다.



public class EmployeeException extends Exception {
	// 직렬화 클래스가 된다는 경고메시지가 뜬다.
	////////////////////////////////////////////////
	// 직렬화 나중에 배우는 부분임 몰라도 됨.
	// 클래스가 직렬화가 되기 위해서 ID 값을 준다는 뜻.
    static final long serialVersionUID = 1L;
	////////////////////////////////////////////////
    
    
	//field
	private int errorCode; // 에러 코드 값은 필드에 저장하면 되고, 메시지는 필드에 저장할 필요는 없다.
	
	//constructor
	public EmployeeException(String message, int errorCode) {    
		super(message);
		this.errorCode = errorCode;
	}
	
	//method
	//getter setter
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}

/*
예시
class Person->Exception {
	//field
	String name->message;
	
	//constructor
	public Person(String name->message) {
		this.name->message = name->message;
	}
}

class Student->EmployeeException extends Person->Exception {
	//field
	String school->code;
	
	//constructor
	public Student->EmployeeException(String name->message, String school->code) { // 두 매개변수를 받아오지만 하나는 부모 클래스에 보내서 저장시키고
		super(name->message);							// 자기 것인 school만 자신의 필드에 저장시켰음.
		this.school = school->code;
	}
}
*/