package ex6_static;

public class CalculatorMain {
	
	public static void main(String[] args) {
	//      ↑ 메모리에 미리 만들어두지 않으면 JVM이 호출하질 못함. CalculatorMain에 static이 있는 이유임.
		
		/*
		Calculator calc1 = new Calculator();
		calc1.add(1,2);
		
		Calculator calc2 = new Calculator();
		calc2.add(1,2);
		
		이렇게 하면 자바는 메모리 낭비라고 생각함. 내부 구조가 같다. 필드가 없다. 저장하는 값이 없다. 메소드(기능)만 있다.
		이럴 때 static을 고려한다.
		*/
		
		// static
		// 인스턴스를 생성하지 않고 곧바로 호출하는 클래스 메소드
		
		Calculator.add(1, 2);
		Calculator.sub(2, 4);
		Calculator.mul(3, 4);
		Calculator.div(4, 2);
		Calculator.mod(5, 2);
		

		Calculator.pow(2, 3);
		Calculator.abs(-5);
		
		
		
	}
}
