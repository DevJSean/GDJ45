package practice3;

import java.util.InputMismatchException;
import java.util.Scanner;

// 메소드에서 예외를 호출 영역으로 던지고
// 메소드들을 호출하는 곳( manage() )에서 예외들을 취합해서 처리하는 방식

// throws ~Exception은 필수로 적어야 하는 것은 아님.
// 적게 되면 호출하는 곳에서 catch하라고 문법적인 강제사항이 생기는 것이다.
// 생략하게 되면 호출하는 곳에서 try catch를 꼭 하지 않는다. catch에서 개발자가 알아서 잘 생각해서 예외처리를 해야한다.
// 명시를 하는 버릇을 하는 게 좋다.


public class Zoo {

	//field
	private Scanner sc;
	private Animal[] animals;
	private int idx;

	//constructor
	public Zoo(int count) {      
		this.sc = new Scanner(System.in);
		this.animals = new Animal[count]; // count를 매개 변수로 받고 그만큼 배열의 길이를 생성하겠다.
	}
	
	//method
	
	// 추가
	// 배열의 범위를 벗어나는 경우 "더 이상 추가할 수 없습니다." 예외 처리 ( count를 벗어나는 입력을 하면 ) 
	public void addAnimal() throws RuntimeException { // throws RuntimeException:  이 메소드는 RuntimeException을 발생시킬 수 있는 메소드입니다.
		if(idx == animals.length)  // 추가된 동물이 배열의 길이와 같아지면 예외를 던지자
			throw new RuntimeException("더 이상 추가할 수 없습니다.");
		System.out.println("===동물 추가===");
		System.out.print("추가할 동물 이름 >>> ");
		String name = sc.next();
		if(Math.random() < 0.5) {  //절반의 확률로 animals 배열에 Lion이나 Tiger가 만들어지게
			animals[idx++] = new Lion(name);
		} else {
			animals[idx++] = new Tiger(name);
		}
		System.out.println(name + "을 추가했습니다.");
	}
	
	// 삭제
	public void removeAnimal() throws RuntimeException {
		if(idx == 0)
			throw new RuntimeException("등록된 동물이 없습니다."); //배열이 비어있는 경우
		System.out.println("===동물 삭제===");
		System.out.print("삭제할 동물 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < idx; i++) {
			if(name.equals(animals[i].getName())) {
				System.arraycopy(animals, i + 1, animals, i, idx -1 - i); // System.arraycopy() 끌어서 덮어쓰기 이용해서 배열의 요소 삭제하기
				animals[--idx] = null;// 마지막 인덱스 값은 항상 null값이 들어있어야 한다.
				System.out.println(name + "을(를) 삭제했습니다.");
				return;
			}
		}
		System.out.println(name + "은(는) 없어용~");
	}
	/*
			배열
	 		  0    1    2    3     4     idx      
	 		| a  | b  | c  | d  |  e   | null | null ...
			arraycopy 사용하면 이렇게 됨.
			  0    1    2    3     4     idx
	 		| b  | c  | d  | e  | null | null | null ...			
	 		idx를 처리해야 함
	 		  0    1    2    3    idx
	 		| b  | c  | d  | e  | null | null | null ...		
	 */
	
	
	
	// 조회
	public void findAnimal() throws RuntimeException {
		if(idx == 0)
			throw new RuntimeException("등록된 동물이 없습니다."); //배열이 비어있는 경우
		System.out.println("===동물 조회===");
		System.out.print("조회할 동물 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < idx; i++) {
			if(name.equals(animals[i].getName())) {
				System.out.println(animals[i] + "입니다."); // animals[i]을 .getName() 쓰지 않고 단순 출력시키면 
				return;										// (Animal에 오버라이드 한)toString이 동작한다.
			}
		}
		System.out.println(name + "은(는) 없어용~");
	}
	
	// 전체 조회
	public void findAllAnimals() throws RuntimeException {
		if(idx == 0)
			throw new RuntimeException("등록된 동물이 없습니다."); //배열이 비어있는 경우
		System.out.println("===전체 조회===");
		for(int i = 0; i < idx; i++)
			System.out.println((i + 1) + "번째 동물 " + animals[i]); //오버라이드 된 toString이 사용됨.
	}
	
	// 메뉴
	public void menu() {
		System.out.println("=============");
		System.out.println("== 1. 추가 ==");
		System.out.println("== 2. 삭제 ==");
		System.out.println("== 3. 조회 ==");
		System.out.println("== 4. 전체 ==");
		System.out.println("== 0. 종료 ==");
		System.out.println("=============");
	}
	
	public void manage() {
		
		while(true) {
			try {
				menu();
				System.out.print("선택(1,2,3,4,0) >>> ");
				switch(sc.nextInt()) {
				case 1: addAnimal(); break; // 반복문 중지 - > 무한루프로 다시 실행
				case 2: removeAnimal(); break;
				case 3: findAnimal(); break;
				case 4: findAllAnimals(); break;
				case 0: System.out.println("프로그램 종료"); return; // 메소드의 종료
				default: System.out.println("잘못된 선택입니다.");
				}
			} catch (InputMismatchException e) {    // InputMisMatchException은 catch문을 적을 때 부모 자식 관계 때문에 항상 RuntimeException보다 먼저 적어야 한다.
				System.out.println("선택은 1,2,3,4,0 중 하나입니다.");
			} catch (RuntimeException e) {  		// 예외 받아주는 catch 문
				System.out.println(e.getMessage()); // 예외 메시지 출력
			}
		}
	}
	
}
