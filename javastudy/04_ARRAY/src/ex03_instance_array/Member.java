package ex03_instance_array;

public class Member {

	// 문제
	// 클래스의 인스턴스를 여러개 저장하기 위한 배열
	
	// field
	private String userId;
	private String name;

	
	// constructor
	public Member() {
	}

	public Member(String userId, String name) {       //필드 값을 채워주는 생성자
		super();
		this.userId = userId;
		this.name = name;
	}
	// new Member();                  이제
	// new Member("admin", "alice");  이 두가지 방법의 인스턴스 생성이 가능해질 것이다.
	
	
	
	// private 필드들에 접근하기 위한 보조적인 수단으로 getter 와 setter 필요
	// method
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
