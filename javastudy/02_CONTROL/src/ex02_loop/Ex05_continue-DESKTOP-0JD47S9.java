package ex02_loop;

public class Ex05_continue {

	public static void main(String[] args) {

		// continue
		// 1. 반복문에서 실행에서 제외할 코드가 있는 경우에 사용한다
		// 2. 반복문의 시작지점으로 흐름이 이동된다.
		
		// 1~100 사이의 정수 중 3의 배수를 제외하고 출력하기
		for(int n = 1; n<=100; n++) {
			if(n % 3 == 0)
				continue;//반복문의 시작 지점으로 흐름이 이동된다. continue 아래 지점은 실행 안함
			System.out.println(n);
		}
		
		for(int n = 1; n<=100; n++) {   //continue는 필수가 아니다. 없어도 된다.
			if(n % 3 != 0)
			System.out.println(n);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
