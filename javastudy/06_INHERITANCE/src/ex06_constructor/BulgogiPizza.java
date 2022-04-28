package ex06_constructor;

// 상속 관계의 클래스들도 
// 생성자 작성은 이클립스에게 맡기자.

// 1. 서브 클래스에 필드(field)가 있는 경우
//		[source] - [Generate Constructor using Fields]

// 2. 서브 클래스에 필드(field)가 없는 경우
//		[source] - [Generate Constructors from Superclass]

public class BulgogiPizza extends Pizza{

	//field
	private String bulgogiOrigin;
	
	//constructor
	public BulgogiPizza(String dough, int cheese, String bulgogiOrigin) {  // 직접 쓰지 말고 source 메뉴로 만드는 방법으로  
		super(dough, cheese);											   // select super constructor to invoke에서 Pizza를 고르고 생성하면 된다.
		this.bulgogiOrigin = bulgogiOrigin;	
	}
	
	//method
	public void info() {
		System.out.println(getDough() + "도우, 치즈" + getCheese() + "g, " + bulgogiOrigin + " 불고기 사용");
		
	}
}
