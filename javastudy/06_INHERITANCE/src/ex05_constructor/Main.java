package ex05_constructor;

public class Main {

	public static void main(String[] args) {
		
		//                   Student
		//                     ↑
		//                   School   School
		//                     ↑        ↑
		//                    Alba     Alba      Alba
		//                     ↑        ↑         ↑
		Alba alba = new Alba("영수", "서울대", "도서관");
		// 디폴트 생성자가 아니기 때문에 맞는 매개변수 값을 넣는다.

		
		alba.eat();
		alba.study();
		alba.work();

		
		System.out.println(alba.getName() + "가 " + alba.getSchool() + "에 다닙니다.");
	}

}
