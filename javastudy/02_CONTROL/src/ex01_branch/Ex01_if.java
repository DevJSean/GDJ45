package ex01_branch;

public class Ex01_if {

	public static void main(String[] args) {

		
		// if 문
		int score = 100; 
		if(score >= 60)
			System.out.println("합격");
	   // ↑자동으로 들여쓰기 됨. 반드시 지키자

		if(score >= 60)
			System.out.println("합격");
		System.out.println("축하합니다"); //if 문과는 상관 없는 문장이다.
		
		if(score >= 60) {
			System.out.println("합격");
			System.out.println("축하합니다");  //if 문에 넣으려면 중괄호를 사용한다
		}
		
		
		// 문제 : 남자는 왼쪽, 여자는 오른쪽
		
		String gender = "남자";
		if (gender.equals("남자"))
			System.out.println("왼쪽");
		
		if(gender.equals("여자"))
			System.out.println("오른쪽");
		
		// 실제로는 else를 사용해야 한다
		
		
		
		
		
	}

}