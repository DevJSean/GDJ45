package prac1;

import java.util.Scanner;

// 돈을 입력받아 오만원권, 만원권, 오천원권, 천원권, 오백원 동전, 백원 동전, 오십원 동전,
// 십원 동전, 오원 동전, 일원 동전이 각각 몇 개로 변환되는지 출력하시오. 이 때 반드시 
// 다음과 같은 배열을 사용하여 반복문으로 처리하시오.
// int[] unit = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };

// 75832원
// 75832 / 50000 -> 1           5만원짜리 한 장 필요하다.
// 75832 % 50000 -> 25832       5만원 한 장 빼고 남은 돈
// 25832 / 10000 -> 2           만원 두 장
// 25832 % 10000 -> 5832
//  ↑money  ↑unit

// money / unit[i] 
// money % unit[i] - > money 갱신

public class Ex03 {

	public static void main(String[] args) {
		
		int[] unit = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };  // 환산할 돈의 단위
		int[] count = new int[unit.length];  // 환산할 돈의 단위는 unit배열의 길이, 나눠서 나온 몫을 여기에 저장할 것임
		
		Scanner sc = new Scanner(System.in);  // System.in : 키보드로부터 데이터를 입력 받는 스캐너

		System.out.print("금액을 입력하시오 >> ");
		int money = sc.nextInt();

		for (int i = 0; i < unit.length; i++) {
			count[i] = money / unit[i];  // count[i]에 몫을 저장, unit[i]의 개수 계산

			if (count[i] > 0) {	  // 몫이 있는 경우, 몫이 0이 나오는것은 안나오게 설정하기
				System.out.println(unit[i] + "원 짜리 : " + count[i] + "개");
				money = money % unit[i];  // money 갱신 
				// money %= unit[i]와 동일
			}
		}

		sc.close();
		
		/*
		int[] unit = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("금액을 입력하시오 >> ");
		int money = sc.nextInt();
		
		int res = money;
		
		for(int i = 0; i < unit.length; i++) {
			if(res / unit[i] > 0) 
				System.out.println(unit[i] + "원 짜리 : " + (res / unit[i]) + "개");
				res = money % unit[i]; 
		}
		
		
		sc.close();
		
		
		*/
	}

}
