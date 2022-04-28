package practice1;

import java.util.InputMismatchException;
import java.util.Scanner;

// 1~100 사이 난수를 하나 발생시키고, 
// 사용자가 해당 난수를 맞히는 게임이다.

// 게임이 끝나면 몇 번만에 맞혔는지 출력해 주고, 
// 1 ~ 100 사이를 벗어난 값을 입력하면 
// "1~100 사이 정수만 입력할 수 있습니다." 

public class UpDownGame {

	// field
	private int rand;  // 발생한 난수
	private int count; // 시도 회수
	private Scanner sc; // 스캐너를 메소드 안에 두지 않고 필드로 두는 이유는, 메소드를 호출할 때마다 스캐너가 잘못 호출될 수도 있기 때문.
	
	// constructor
	public UpDownGame() {    // this 생략 가능.
		this.rand = (int)(Math.random() * 100 + 1);
		this.count = 0; // 생략 가능
		this.sc = new Scanner(System.in);
	}
	
	// method
	
	// 입력 메소드
	public int input() /* throws InputMismatchException, RuntimeException */{
		count++; // 시도 횟수는 input을 호출할 때마다 늘리는 것으로 한다.
		System.out.print("1~100 사이의 입력 >>> ");
		int n = sc.nextInt();
		if (n < 1 || n > 100)
			throw new RuntimeException();
		return n;
	}
	
	// 실행 메소드
	public void play() {
		
		while(true) {
			
			try {
				
				int n = input();   // 예외 코드를 던지는 부분은 input 메소드이지만 실행하는 건 play 안에 있음. try 블록을 잘 씌워주자.

				if (n < rand) {
					System.out.println("Up");
				} else if (n > rand){
					System.out.println("Down");
				} else {
					System.out.println(rand + " 정답! " + count + "번만에 맞혔습니다.");
					sc.close();
					break; //play의 종료를 의미
				}
					
			   // 상속 관계를 이해하고 InputMismatchException을 먼저 사용해야 둘 다 예외처리 된다.
			} catch (InputMismatchException e) { // InputMismatchException은  RuntimeException의 자식의 자식임
				// sc.nextInt()로 입력되지 못한 입력이 System.in에 남아있어서 이를 제거해야 한다.
				sc.nextLine(); // 입력만 받아주고 저장은 하지 않는다.
				System.out.println("정수만 입력할 수 있습니다.");
			} catch (RuntimeException e) {  // 자바가 알아낼 수 없는 예외 찾기.
				System.out.println("1~100 사이 정수만 입력할 수 있습니다.");
			}  // 자바는 예외를 순서대로 던진다. InputMismatchException을 던지면 runtimeException이 먼저 잡아먹는다. InputMismatchException은 실행되지 않는다: dead code
			/* catch (InputMismatchException e) { // InputMismatchException은  RuntimeException의 자식의 자식임
				System.out.println("정수만 입력할 수 있습니다.");
			}*/
			
		} // while(true)
	} // play()
	
	
	
	
	
	
	
	
	
}
