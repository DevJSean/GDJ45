package practice2;

import java.util.InputMismatchException;
import java.util.Scanner;

// try catch를 메소드의 내부에서 직접 하고 있음.
// 해당 메소드들을 호출하는 곳이 아님.


public class BankAccount {

	// field
	private String owner; // 예금주
	private String accNo; // 계좌번호
	private long balance; // 잔고
	private Scanner sc; // 연습을 위한 입력
	private BankAccount[] accounts; // 자주 사용하는 계좌
	private int idx; // 배열 사용을 위한 인덱스
	

	//constructor 
	public BankAccount(String owner, String accNo, long balance) {
		super();
		this.owner = owner;
		this.accNo = accNo;
		this.balance = balance;
		sc = new Scanner(System.in);
		accounts = new BankAccount[3]; // 자주 사용하는 친구 계좌 3개까지 등록하겠다.
	}
	
	
	// method
	
	// 자주 쓰는 친구 계좌 등록
	public void addAccount() {   // 지금은 try catch 연습하기 위해서 이렇게 하지만, 실 개발에서는 if로 처리하는 게 더 좋다.
		try {
			System.out.println("===자주 쓰는 계좌 등록(" + (accounts.length - idx) + "개 등록 가능)==="); // 0개 등록 가능인데 계속 입력하는 사람이 있는 경우 에외 발생
			System.out.print("예금주 >>> ");														      // ArrayIndexOutOfBoundsException : 인덱스 사용 범위 초과로 입력하려고 하는 경우
			String owner = sc.next();
			System.out.print("계좌번호 >>>");
			String accNo = sc.next();
			System.out.print("잔고 >>> ");
			long balance = sc.nextLong();
			accounts[idx++] = new BankAccount(owner, accNo, balance);
			System.out.println("계좌 등록 성공!");
		} catch(ArrayIndexOutOfBoundsException e) {  
			System.out.println("더 이상 계좌를 등록할 수 없습니다.");
		}
	}
	
	
	// 입금
	public void deposit() {
		// 마이너스 입금하면
		// "-10000원 입금 불가" 예외 메시지 출력
		try {
			System.out.println("계좌를 선택하세요.");
			
			System.out.println("===입금하세요===");
			System.out.print("입금액 >>> ");
			long money = sc.nextLong();
			if(money < 0)
				throw new RuntimeException(money + "원 입금 불가");
			balance += money;
			System.out.println("입금 성공!");
			
		}catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// 출금
	public void withdrawal() {
		// 마이너스 출금을 하면
		// "-10000원 출금 불가" 예외메시지 출력
		// 잔고보다 큰 금액을 출금하면 
		//  "잔고 부족" 예외메시지 출력
		try {
			System.out.println("===출금===");
			System.out.print("출금액 >>> ");
			long money = sc.nextLong();
			if(money < 0)
				throw new RuntimeException(money + "원 출금 불가");
			else if(money > balance)
				throw new RuntimeException("잔고 부족");
			balance -= money;
			System.out.println("출금 성공!");
			
		}catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// 이체를 위한 입금 메소드, 외부에 공개할 이유가 없어서 private
	private void deposit(long money) { // long money: 입금하려는 금액, 메소드 이름이 같지만 매개변수가 있어서 가능하다.
		try {
			if(money < 0)
				throw new RuntimeException(money + "원 입금 불가");
			balance += money;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// 이체를 위한 출금 메소드, 외부에 공개할 이유가 없어서 private
	// 반환 타입 : long       : 실제로 출금된 금액
	// 매개 변수 : long money : 출금하려는 금액 
	private long withdrawal(long money) { // 메소드 이름이 같지만 매개변수가 있어서 가능하다.
		try {
			if(money < 0) {
				throw new RuntimeException(money + "원 출금 불가");
			} else if (money > balance)
				throw new RuntimeException("잔고 부족");
			balance -= money;
			System.out.println("내 통장에서" + money + "원이 출금되었습니다.");
			
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			money = 0; // 맞지 금액 넣었을 때 0원을 반환 
		}
		return money;
	}
	
	
	// 이체 ( 출금액 -> 입금액 )
	public void transfer() {
		System.out.println("===이체===");
		System.out.print("이체할 예금주 >>");
		String owner = sc.next();
		for(int i = 0; i < idx; i++) {    // for문으로 예금주를 찾고 이체 작업까지 한다.
			if(owner.equals(accounts[i].owner)) {  // 동일한 에금주가 발견되면
				System.out.println("이체할 금액 >>> ");
				long money = sc.nextLong();
				accounts[i].deposit(withdrawal(money)); //이체 작업을 함. < accounts[i] : 이체하려는 상대 계좌, deposit(이 값 입금), withdrawal(money) : 내 계좌에서 출금 >
				return; // 이체가 잘 되면 return으로 transfer 메소드를 끝냄. 그렇게 되면 밑에 예금주가 없습니다 출력 부분이 나오지 않음.
			} //if()			
		} // for()
		// 만약 for문이 끝날 때까지 일치하는 예금주가 없으면 if가 동작하지 않을 것이고 return 실행 되지 않음.
		// 그럼 for문이 끝나고 아래로 내려가서 출력 함
		System.out.println(owner + " 예금주가 없습니다.");
	} // transfer()
	
	
	// 조회
	public void inquiry() {
		System.out.println("===내 통장 조회===");
		System.out.println("예금주" + owner + " 계좌번호 " + accNo + " 잔고 " + balance + "원");
		System.out.println("자주쓰는 친구들의 계좌 현황");
		for(int i = 0; i < idx; i++) {
			System.out.println("예금주 " + accounts[i].owner + " 계좌번호 " + accounts[i].accNo + " 잔고 " + accounts[i].balance + "원");
		}
	}
	
	
	// 메뉴
	public void menu() {
		System.out.println("=================");
		System.out.println("1. 계좌등록");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 이체");
		System.out.println("5. 전체 계좌 조회");
		System.out.println("0. 종료");
		System.out.println("=================");
	}
	
	
	// 계좌관리실행
	public void manage() {
		while(true) {
				try { // 무한루프 안에 넣는 이유는, 예외가 발생했다고 해서 전체적인 실행이 끊기지 않게 하려고.
					menu();
					System.out.print("선택(1,2,3,4,5,0) >>>");
					switch(sc.nextInt()) {                      //예외. 정수를 입력받기로 했는데 다른 것을 입력했을 때
					case 1: addAccount(); break;
					case 2: deposit(); break;
					case 3: withdrawal(); break;
					case 4: transfer(); break;
					case 5: inquiry(); break;
					case 0: System.out.println("계좌관리종료"); return; // break return 둘 다 가능
					default: System.out.println("잘못된 선택입니다. 다시 시도하세요.");
					}
				} catch (InputMismatchException e) {
					sc.next();          // 무한루프로 괴롭히는 입력을 처리
					System.out.println("작업 선택은 정수(1,2,3,4,5,0) 입니다.");
				}
		}
	}
}
