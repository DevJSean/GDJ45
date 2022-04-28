package prac1;

import java.util.Calendar;
import java.util.Scanner;

public class Player {
	
	//field
	private Scanner sc;
	private String name;

	//constructor
	public Player(String name) {          //Ex07에서 받은 매개변수 강아지를
		this.name = name;                 //this로 필드 name에 넣는 것
		sc = new Scanner(System.in);
	  //if(sc == null)                    sc 값이 비어있을 때만 넣는 방법
	  //	sc == new Scanner(System.in);
	}

	public String getName() {             //sc에 대한 입출력은 필요 없음
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int turn() {
		System.out.print(name + "님 시작하려면 <Enter>를 누르세요.");
		sc.nextLine();  // <Enter>키 입력받기. 입력할 데이터를 저장하려면 변수(String str = ...)를 넣겠지만 넣을 필요 없음
		Calendar calendar = Calendar.getInstance();
		int startSecond = calendar.get(Calendar.SECOND);       // Calendar는 1분 10초나 2분 10초나 초는 다른 것을 구분 못한다는 약점이 있음
		System.out.println("=== 시작 시간(초): " + startSecond);

		System.out.print("10초가 된 것 같으면 <Enter>를 누르세요.");
		sc.nextLine();  // <Enter>키 입력
		calendar = Calendar.getInstance();				// 현재 날짜 + 시간을 읽어들임
		int endSecond = calendar.get(Calendar.SECOND);
		System.out.println("=== 종료 시간(초): " + endSecond);

		if (endSecond < startSecond)
			endSecond += 60;	// 60초 후로 만들어야 함
								// startSecond 이 59초 이고, endSecond 이 9초 인 상황을 생각해보자.
								// startSecond 은 59초 이고, endSecond 은 69초 로 변경해서 계산한다는 의미이다.

		return endSecond - startSecond;
	}
	
}

/*

public double turn() {
	System.out.print(name + "님 시작하려면 <Enter>를 누르세요.");
	sc.nextLine();  // <Enter>키 입력받기. 입력할 데이터를 저장하려면 변수(String str = ...)를 넣겠지만 넣을 필요 없음
	long begin = System.currentTimeMillis(); 최초 엔터 시간
	System.out.println("10초가 된 것 같으면 <Enter>를 누르세요");
	sc. nextLine();
	long end = System.currentTimeMillis(); 두 번째 엔터 시간
	return (end - begin) * 0.001;  1000분의 1초니까 / 1000 반환 타입은 int인데 계산 결과는 long이다.
	정확한 비교를 위해turn()의 반환 타입을 double로 바꾸고 * 0.001을 한다.
*/	
