package prac1;

public class BankAccount {

	// field
	private String accNo;  // 계좌번호
	private long balance;  // 통장잔고, 잔액
	
	// constructor
	public BankAccount(String accNo, long balance) {
		this.accNo = accNo;
		this.balance = balance;
	}
	
	
	// method
	// 입금
	public void deposit(long money) {
		if (money <= 0) {  // 음수를 입금하려는 행위를 방지
			return;
		}
		balance += money;
	}
	
	// 출금
	// 1) 결과타입 : long  (실제로 출금된 금액)
	// 2) 메소드명 : withdraw
	// 3) 매개변수 : long money  (출금해야 될 금액)
	public long withdraw(long money) {
		if (money <= 0 || money > balance) { //음수를 출금하려고 하거나 잔액보다 큰 액수를 출금하려는 행위 방지
			return 0;  // 출금된 금액이 0원이다.
		}
		balance -= money;
		return money;
	}
	/*
	public boolean withdraw(long money) {
		if (money <= 0 || money > balance) { 
			return false;  
		}
		balance -= money;
		return true;
	}
	*/
	
	// 이체
	public void transfer(BankAccount other, long money) {
		// me가 BankAccount 타입이기 때문에 other도 BankAccount타입이어야 함.
		// 출금이 먼저 동작, 그 다음 입금 순서로 해야 함.
		// 출금 성공해야 입금도 할 수 있게 해야함.
		// other : 입금
		// this  : 출금
		// if(this.withdraw(money))
		//	   other.deposit(money); 위 출금 메소드 반환 타입을 boolean타입으로 바꾸면 가능한 방법.
		
		
		// 내 계좌에서 출금하기 : withdraw(money)
		// 상대 계좌에 입금하기 : other.deposit(money)
		// 이체 : 내 계좌에서 출금된 금액만큼만 상대 계좌에 입금하기 
		other.deposit(withdraw(money));
	}
	
	// 조회
	public void inquiry() {
		System.out.println("계좌번호: " + accNo + ", 잔액: " + balance + "원");
	}
	
}
