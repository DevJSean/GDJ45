package ex04_has_a;

public class Main {

	public static void main(String[] args) {


		Tv tv = new Tv();
		
		tv.chUp();
		tv.chDown();

		
		/////////////////////////////////////////////////////////////
		
		
		// null 값이 발생하지 않도록 new RemoteCon(); 생성자
		RemoteCon remotecon = new RemoteCon();
		Radio radio = new Radio(remotecon); //생성자 필요하다.
		
		// 상속 관계는 아니지만 chUp chDown이 있다는 것은 Radio클래스에 해당 메소드가 있어야 한다는 것.
		// 근데 이대로 그냥 실행하면 nullpointerexception이 발생함.
		radio.chUp();
		radio.chDown();
		
	}

}
