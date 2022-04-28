package ex08_override;

public class Main {

	public static void main(String[] args) {

		Espresso e = new Espresso("콜롬비아", 50); // Espresso 생성자
		
		e.taste(); //coffee였으면 씹는 맛이었을텐데, 쓰다로 잘 override 됨.
		e.info();

	}

}
