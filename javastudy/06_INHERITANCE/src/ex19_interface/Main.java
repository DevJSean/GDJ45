package ex19_interface;


public class Main {

	public static void main(String[] args) {

		// Phone 타입으로 선언
		Phone p1 = new SmartPhone();
		p1.call();
		p1.sms();
		((Computer)p1).playGame();   // Computer의 메소드를 사용하려면 downcasting
		((Computer)p1).playMusic();
		
		// Computer 타입으로 선언
		Computer p2 = new SmartPhone();
		p2.playMusic();
		p2.playGame();
		((Phone)p2).call();       // Phone의 메소드를 사용하려면 downcasting
		((Phone)p2).sms();
		
		// SmartPhone 타입으로 선언
		// Phone을 상속받고, Computer를 구현한 SmartPhone에서 모든 기능을 수행할 수 있다.
		SmartPhone p3 = new SmartPhone(); 
		p3.call();
		p3.sms();
		p3.playMusic();
		p3.playGame();
		
	}

}
