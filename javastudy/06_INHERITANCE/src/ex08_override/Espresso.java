package ex08_override;

// Espresso is a Coffee.

public class Espresso extends Coffee {

	// field
	private int water;
	
	// constructor
	public Espresso(String bean, int water) {
		super(bean); //부모 클래스를 먼저 불러온다. bean에 넣는 값은 부모 클래스에 전달된다 
		this.water = water;
	}
	
	
	// Espresso의 기능은 Coffee의 taste(), info()
	// 근데 Espresso의 맛이 Coffee의 맛인가? Espresso의 정보는 물 정보인데 Coffee의 정보를 사용해도 되는가? - 맞지 않는다
	// Coffee 클래스의 기능인 taste, info를 
	// Espresso 클래스가 그대로 사용할 수 없다.
	// 이런 경우에는
	// Espresso 클래스가 taste, info를 다시 만들어 사용한다 - method override
	
	// method
	@Override
	public void taste() {
		System.out.println("쓰다."); 
	}
	
	@Override
	public void info() {
		// 원두 정보 출력을 위해서 Coffee 클래스의 info 메소드를 호출할 수 있다.(기존의 메소드를 사용할 수 있다)
		// info(); 이렇게 쓰면 java는 Espresso의 info메소드라고 인식한다
		// Coffee 클래스는 슈퍼클래스니까 super 키워드를 활용해서 사용할 수 있다.
		super.info(); // 슈퍼 클래스의 info 메소드를 호출할 것이다
		System.out.println("물: " + water + "ml");
	}
	
	
	
	
	
	
	
	
	
	
}
