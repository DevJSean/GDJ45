package prac1;

import java.util.Scanner;

// 커피 메뉴를 입력받아 가격을 알려주는 프로그램을 구현하시오. switch 문을 이용하여 구현
// (에스프레소, 카푸치노, 카페라떼는 3500원, 아메리카노는 2000원이다.)

public class Ex02 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("무슨 커피 드릴까요? >>> ");
		String order = sc.next();
		int price = 0;
		switch (order) {
		case "에스프레소":
		case "카푸치노":
		case "카페라떼":
			price = 3500;
			break;
		case "아메리카노":
			price = 2000;
			break;
		default:
			System.out.println(order + "는 메뉴에 없습니다.");
		}
		
		if (price != 0)
			System.out.print(order + "는 " + price + "원입니다.");
		
		sc.close();
		
		/*
		Scanner sc = new Scanner(System.in);
		
		System.out.print("무슨 커피 드릴까요? >>> ");
		String order = sc.next();
		
		switch(order) {
		case "에스프레소": 
		case "카푸치노": 
		case "카페라떼":
			System.out.println(order + "는 3500원입니다.");
			break;
		case "아메리카노":
			System.out.println(order + "는 2000원입니다.");
			break;
		default :
			System.out.println(order + "는 메뉴에 없습니다.");
		}
		*/
		
		

		
		
		
		
		
		
		
		sc.close();
	}

}
