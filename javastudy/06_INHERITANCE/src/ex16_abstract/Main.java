package ex16_abstract;

public class Main {

	public static void main(String[] args) {

		// 추상 클래스의 인스턴스 생성은 불가능하다.
		// Shape = shape = new Shape("도형");  추상 클래스라서 불가능하다
		
		// 어쩔 수 없이 인스턴스를 만들어야 한다면, Shape shape = new shape() 자동완성 anonymous inner type
		/*
		Shape shape = new sh
		Shape shape = new Shape() {
			
			@Override
			public double getArea() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		*/
		
		
		Shape rectangle = new Rectangle("사각형", 3, 4);
		System.out.println(rectangle.getName() + " " + rectangle.getArea());
		
		Shape circle = new Circle("원", 1.5);
		System.out.println(circle.getName() + " " + circle.getArea());
		
		
	}

}
