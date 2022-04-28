package ex2_method;

public class VendingMachine {
//여기도 메인메소드가 없다.
	
		// 메소드
		// 1. 클래스가 가지고 있는 기능을 의미한다.
		// 2. 함수와 같은 개념
		// 3. 형식                          데이터가 매개변수(입력)에 저장돼서 반환값(출력)으로 나온다. 반환값의 타입을 신경써서 반환타입을 지정한다.
		// 	  반환타입 메소드명(매개변수) {    
		//          실행코드
		//          return 반환값
		//    } <-중괄호 필수
		
		// 커피 뽑는 메소드
		// 1. 입력
		//    1) 돈
		//    2) 버튼(1: 밀크커피, 2: 블랙커피)
		// 2. 출력
		//	  밀크커피 1잔
		//    블랙커피 2잔
		
		String getCoffee(int money, int button) { 
			
			String res = "";
			
			if(button == 1)
				res += "밀크커피 ";
			else if(button == 2)
				res += "블랙커피 ";
			
			res += ((money / 500) + "잔"); //커피 잔수, 500원씩 넣는다고 생각
			
			return res;
			
		}

		
		
		
		
}
