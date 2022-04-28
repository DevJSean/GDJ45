package prac1;

import java.util.Scanner;

// 점수를 몇 개 저장할지 (최대 100개) 입력받아서 해당 길이를 가진 배열을 생성하고,
// 이곳에 1에서 100사이 범위의 정수를 랜덤하게 삽입하시오. 같은 값은 생성하지 못하도록
// 설정하고 생성된 배열을 출력하시오.

public class Ex04 {

	// exists()
	// 1. 발생한 난수가 기존 배열에 존재하는지 확인하는 메소드
	// 2. 존재하면 true, 존재하지 않으면 false 반환
	// 동일한 클래스에서 main메소드가 static 처리가 되어 있기 때문에 exists 메소드도 static 처리해야 함. 
		
	// 배열 arr의 인덱스 0부터 from까지 rand가 존재하는가?
	public static boolean exists(int arr[], int from, int rand) {      // exists(배열 arr, 인덱스(어디까지 비교해야 되는지), 발생시킨 난수) 
		for (int i = 0; i < from; i++) {
			if (arr[i] == rand)
				return true;  			// 중복이 존재하면 true 리턴
		}								// else 처리하면 안됨.
		return false;  					// 중복이 존재하지 않으면 false 리턴, 중복이 있었으면 그 지점에서 for문이 끝남.  
	}									// else 처리하지 않고 해당 줄에 return false 해야한다.
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("몇 개의 랜덤을 생성할까요? >>> ");
		int n = sc.nextInt();

		if (n <= 0 || n > 100) {
			System.out.print("다음에는 1~100사이로 입력하세요!");
			sc.close();
			return;  // 프로그램 종료하라. 해당 메인 메소드가 void인 경우에만 사용 가능.
		}
		
		int arr[] = new int[n]; // n개의 정수 배열 생성

		// 배열 arr에 난수(1~100) 저장
		// 중복 생성되면 재생성
		for (int i = 0; i < arr.length; i++) {
			int rand = (int) (Math.random() * 100 + 1);  // 1~100 범위의 난수 생성
			if (exists(arr, i, rand)) {  // 중복이 있으면 true 반환, 없으면 false 반환하는 메소드 호출
				i--;  // continue로 가서 돌아가면 난수를 배열에 저장시키지 않기 때문에 값이 빔. 다시 난수를 발생시켜야 하므로 늘어날 인덱스를 대비하기 위해 미리 하나 줄임.
				continue; // 중복이 발생한 경우 난수를 배열에 저장시키지 않기 위해 continue로 for문 초반으로 돌아간다.
			}
			arr[i] = rand;  // 난수를 배열에 저장
		}

		// 배열 arr의 출력
		// 한 줄에 10개씩
		for (int i = 0; i < arr.length; i++) {
			if(i != 0 && i % 10 == 0)  // 한 줄에 10개의 숫자를 보여주고 다음 줄로 넘어가기 위함, i 가 10의 배수일 때 줄바꿈
				System.out.println();
			System.out.println(arr[i] + "\t");
		}

		sc.close();
		
		
	} // main 끝나는 지점
	
	/*
	Scanner sc = new Scanner(System.in);
	
	System.out.print("몇 개의 랜덤을 생성할까요? >>> ");
	int num = sc.nextInt();
	if(num >100)
		System.out.println("다음에는 1~100 사이로 입력하세요!");
	else {
		int[] arr = new int[num];
		for(int i = 0; i < num; i++) {
			arr[i] = (int)(Math.random() * 100) + 1;
			System.out.print(arr[i] + "\t");
			}
	}
	sc.close();
	*/
	
}
