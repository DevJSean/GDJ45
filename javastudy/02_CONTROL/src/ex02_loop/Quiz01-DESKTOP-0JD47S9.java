package ex02_loop;

public class Quiz01 {

	public static void main(String[] args) {
		
		// 평점에 따른 별(★) 출력하기
		// loop 반복문으로 풀어야 함
		
		int star = 5;
		String res = "";
		
		for(int n = 1; n <= star; n++)
			res += "★";
		
		System.out.println(res);

	
		//  2x1=2  3x1=3  4x1=4  ... 9x1=9
		int n = 1;
		for(int dan = 2; dan <= 9; dan++)
			System.out.print(dan + "x" + n + "=" + (dan * n) + " ");
		System.out.println("");
		
		// 2x2=4 3x2=6 4x2=8 ... 9x2=18
		// ...
		
		for(int n2=1; n2<=9; n2++) {   //중괄호 필수
			for(int dan = 2; dan <= 9; dan++)
				System.out.print(dan + "x" + n2 + "=" + (dan * n2) + "\t"); // \t: 탭 문자(다음 탭으로 이동하겠다는 뜻)
			System.out.println(); //줄바꿈
		}
	
	
	
	
	
	}

}
