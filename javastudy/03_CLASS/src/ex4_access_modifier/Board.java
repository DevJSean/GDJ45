package ex4_access_modifier;

// 접근 제어 지시자
// 1. 클래스를 구성하는 요소(필드, 메소드, 생성자)들의 접근 권한을 의미한다.
// 2. 종류
//		1) private: 클래스의 내부에서만 접근할 수 있다. 외부에 공개하려 하지 않을 때.
//		2) default: 동일한 패키지에서만 접근할 수 있다. (기본값, 지금까지 모두 default 접근 권한을 사용한 것, 이제부터 안씀)
//		3) protected: 동일한 패키지 + 상속 관계의 다른 패키지에서만 접근할 수 있다.
//		4) public: 누구나 접근할 수 있다.
//		private < default < protected < public
// 3. 일반적인 적용
//		1) 필드: private
//		2) 메소드: public
//		3) 생성자: public

public class Board {
	
	// 필드
	private int boardNo; // 접근 제어 지시자는 항상 맨 앞에 적는다.
	private String title;  
	
	// 생성자    (모든 생성자는 클래스와 이름이 같아야 한다)
	public Board() {
		
	}
	public Board(int boardNo, String title) {
		this.boardNo = boardNo;
		this.title = title;
		
	}
	
	// 메소드
	
	//반환타입 메소드명(매개변수) {
	//}
	
	// void 메소드명(매개변수) {     
	//}
	
	//void : 반환값/반환타입이 없을 때 사용. return도 없다.
	
	
	
	// setter
	// 1. 필드에 값을 저장하기 위한 메소드 (setBoardNo, setTitle)
	// 2. set + 필드명으로 메소드명을 결정한다.
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	// getter
	// 1. 필드의 값을 반환하기 위한 메소드         외부에서는 필드의 값을 못보니까 반환만 시켜주는 것임.
	// 2. get + 필드명으로 메소드명을 결정한다.
	public int getBoardNo() {
		return boardNo;
	}
	public String getTitle() {
		return title;
	}
	
	
	
	
	// 필드에 저장된 값을 출력하는 메소드
	public void info() {     // 매개변수 공백, boardMain에서 넘어오는 매개변수가 없다.
		System.out.println("글번호: " + boardNo);
		System.out.println("글제목: " + title);
	}
	
	
	
	
}
