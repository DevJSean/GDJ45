package ex02_loop;

public class Ex03_nested_for {

	public static void main(String[] args) {


		// 1일차 1교시 수업입니다.
		// 1일차 2교시 수업입니다.
		// ...
		// 3일차 8교시 수업입니다.
		
		for(int day = 1; day <= 3; day++)
			for(int hour = 1; hour<= 8; hour++)
				System.out.println(day + "일차 " + hour + "교시 수업입니다.");

		// 문제: 2단부터 9단까지 모두 출력하기
		for(int n = 2; n <= 9; n ++)
			for(int m = 1; m <= 9; m++)
				System.out.println(n + " X " + m + " = " + n*m);
		
		// 2단부터 9단 사이에 --------------- 출력하기
		for(int n = 2; n <= 9; n ++) {  // 중괄호가 필수적으로 들어감
			for(int nm = 1; nm <= 9; nm++)
				System.out.println(n + " X " + nm + " = " + n * nm);
			System.out.println("--------------");
		}
	}

}
