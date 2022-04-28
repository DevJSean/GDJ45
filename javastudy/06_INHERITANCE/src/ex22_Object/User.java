package ex22_Object;

public class User {

	// field
	private String userId;
	private String userPw;
	
	// constructor
	// public Object() {}   : Object 클래스의 생성자 모양, 아무런 일도 하지 않는다.
	public User(String userId, String userPw) {
		super();               //Object 클래스의 생성자 호출
		this.userId = userId;
		this.userPw = userPw;
	}

	// method
	
	// getter setter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	// Object 클래스의 equals 메소드 오버라이드
	// Ctrl + space 누르면 equals(Object obj)라고 바로 나온다
	@Override
	public boolean equals(Object obj) {  	                 // super.equals(obj) 필요 없는 부분이니 지우고 내 상황에 맞는 return 값으로 수정한다.
	//  return userId.equals(   ((User)obj).userId  ); 같은 결과가 나오지만 아랫 줄이 바람직하다. 		
		return this.userId.equals(   ((User)obj).getUserId()  );  	
 		
		// Main에서 user1(나, 메소드를 사용하는 주체, this)이 equals(메소드)를 이용해서 user2를 불러왔다.
		// user2는 new User("admin", 123456)을 통해 object에 저장됐다.
		
		// this.user1 : user1의 userId
		// ((User)obj).getUserId() : user2의 userId
		// 매개변수가 Object obj니까 User 클래스로 downcasting한다
	}

	
	
	// Object 클래스의 toString 메소드 오버라이드
	// Source 메뉴의 Generate toString을 이용하자
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + "]";
	}
	

	
}
