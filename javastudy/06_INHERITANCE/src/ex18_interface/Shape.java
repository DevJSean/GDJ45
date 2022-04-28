package ex18_interface;

// 생성 메뉴창에서 create a java interface 고른다. 
// 클래스 자리↓에 interface로 되어있음
public interface Shape {

	// final 상수
	public final double PI = Math.PI;
	
	// abstract method 추상 메소드
	// 모든 도형은 넓이를 구할 때 반환타입이 double인 getArea메소드를 사용해라.
	public double getArea(); // public abstract double getArea();  abstract를 빼도 내부적으로 abstract 처리를 하기 때문에 이 코드처럼 작동한다.
	public void info(); // public abstract void info();
	
	// default method 디폴트 메소드
	// 메소드 정의할 때 default를 적고 본문(중괄호)를 가진다.
	// Shape 밑의 Rectangle, Triangle, Circle 등이 공통적으로 가지는 기능이 있을 때 디폴트 메소드를 사용한다.
	public default void message() {
		System.out.println("나는 도형입니다.");
	}
	
	
	
}
