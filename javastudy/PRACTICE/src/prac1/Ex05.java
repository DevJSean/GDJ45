package prac1;

import java.util.Scanner;

// 3명의 학생의 점수를 입력받아서 평균 점수와 1등의 이름과 꼴등의 이름을 출력하시오.

public class Ex05 {

	public static void main(String[] args) {

		String[] names = new String[] { "피카츄", "뽀로로", "브레드" };  // String[] names = { "뽀로로", "피카츄", "브레드"};
		int[] scores = new int[names.length];
		
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < scores.length; i++) {
			System.out.print(names[i] + "의 점수 입력 >>> ");
			scores[i] = sc.nextInt();
		}
		/* 점수를 랜덤 값으로 받으려면
		for (int i = 0; i < scores.length; i++) {
			scores[i] = (int)(Math.random() * 101);
		 */
		
		// 합계, 최대, 최소                   첫 학생의 점수 값을 total, max, min에 전달하는 게 시작이다.
		int total = scores[0];              // 0번 인덱스를 이미 넣었으니 반복문에서는 i가 1에서부터 시작해야 한다.
		int max = scores[0];
		int min = scores[0];
		int top = 0;
		int bottom = 0;
		
		for (int i = 1; i < scores.length; i++) {
			total += scores[i];
			if (max < scores[i]) {  // 비교하는 인덱스의 값이 더 큰 경우
				max = scores[i];
				top = i;			// 그 학생의 이름을 얻어내야 하기 위해 그 인덱스의 번호를 따로 저장
			}
			if (min > scores[i]) {
				min = scores[i];
				bottom = i;
			}
		}
		
		double average = (double)total / scores.length; // int / int는 int이기 때문에 double이 될 수 없다. 그래서 total을 double로 casting한다.
		
		System.out.println("평균: " + average + "점");
		System.out.println("1등 점수 : " + max + "점");
		System.out.println("1등 이름 : " + names[top]);
		System.out.println("꼴등 점수 : " + min + "점");
		System.out.println("꼴등 이름 : " + names[bottom]);
		
		sc.close();

	}

}
