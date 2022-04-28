package ex05_throws;

public class Main {

	
	// 특정 메소드가 예외를 발생시킬 때
	// 예외가 발생하는 메소드의 내부에서 예외처리 하는 방법
	// 메소드를 호출하는 곳에서 예외처리 하는 방법이 있다. (UpDownGame, 지금의 main메소드)
	// 호출하는 곳에서 예외처리를 하면 여러 메소드를 호출할 때 한번에 처리할 수 있다.
	// try catch를 각 메소드 별로 하고 싶으면 예외가 발생하는 메소드에 try catch 적으면 된다.
	
	
	// m1 메소드는 NumberFormatException을 발생시킨다. ↓↓↓   throws. s가 붙는 것을 명심
	public static void m1()             throws NumberFormatException/*, Exception */{

		String strAge = "20"; // 맞지 않는 값을 넣으면?
		int age = Integer.parseInt(strAge);
		System.out.println("입력된 나이는 " + age + "살입니다.");
	}
	
	public static void m2() throws NumberFormatException{
		String strHeight = "180.5"; // 맞지 않는 값을 넣으면?
		double height = Double.parseDouble(strHeight);
		System.out.println("입력된 키는 " + height + "cm입니다.");
	}
	
	public static void main(String[] args) {

		try {
			m1();
			m2();
		} catch (NumberFormatException e) {
			System.out.println("예외가 발생했습니다.");
		}
		
		
		
	}

}
