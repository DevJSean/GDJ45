package ex01_is_a;

public class Main {

	public static void main(String[] args) {

		// 부모 클래스(super)의 동작은 확인해 볼 필요가 없다.
		
		//클래스(타입)   인스턴스(변수) =  new 연산자   생성자명
		Student             student     =     new       Student();
		
		student.eat();
		student.study();
		
		Alba alba = new Alba();
		
		alba.eat();
		alba.study();
		alba.work();
		
	}

}
