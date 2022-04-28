package ex3_constructor;

public class ComputerMain {

	public static void main(String[] args) {


		// Computer 클래스의 인스턴스 com 생성
		
		//             ↓ 인스턴스(객체), 변수처럼 생각하면 됨
		//    Computer com = new Computer();   // 생성자 호출, new 필요함.
		// 메소드는 항상 뒤에 () 붙음 ↑ 근데 이건 특별한 method임, 인스턴스를 생성할 때 쓰는 method임 : 생성자(constructor) 메소드
		//                      일반적인 메소드보다 약속이 많음. 이름이 Class와 같아야 한다.

		Computer com = new Computer("LG", "gram", 200); // 인자를 3개 넣었는데 저장할 매개변수를 지정하지 않으면 오류가 남.
														// 생성자에서 매개변수를 지정하면 오류가 풀림. 		      
	
	
		System.out.println(com.maker);  // 실제로 필드 값을 찍어봤을 때 아무것도 나오지 않음
		System.out.println(com.model);
		System.out.println(com.price);
	
		// 왜냐면 위에 인자 3개는 필드에 저장한 것이 아니고 매개변수에 저장한 것임.
		// 사전에 생성자에서 매개변수에 저장된 값을 필드로 전달해주는 코드(this.maker = maker;)를 넣으면 해결됨.
	
	}

}
